package org.mcudzik.backend.security;

import lombok.RequiredArgsConstructor;
import org.mcudzik.backend.exception.UnauthorizedSpotifySessionAccessException;
import org.mcudzik.backend.model.Session;
import org.mcudzik.backend.model.UserTypes;
import org.mcudzik.backend.repository.SessionRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthorizationService {

    private final SessionRepository sessionRepository;
    private final UserDetailsService userDetailsService;

    public boolean validateSessionUserAccess(UUID userId, UUID sessionId) throws UnauthorizedSpotifySessionAccessException {
        Session session = sessionRepository.findById(sessionId).orElseThrow(() -> new UnauthorizedSpotifySessionAccessException("Session not found"));

        UserDetails user = userDetailsService.loadUserByUsername(userId.toString());
        if(user == null){
            throw new UnauthorizedSpotifySessionAccessException("User not found");
        }

        var authorities = user.getAuthorities().toArray();
        var role = authorities[0].toString();


        //if user is a spotify user they need to be the owner of the session
        if(role.equals(UserTypes.SPOTIFY.name())){

            var sessionOwner = session.getOwner();
            return sessionOwner.getId().equals(userId);

        }
        //if user is an anon user they need to be a member of the session
        else if (role.equals(UserTypes.ANON.name())) {

            var sessionUsers = session.getAnonUsers();
            for(var anonUser : sessionUsers){
                if(anonUser.getId().equals(userId)){
                    return true;
                }
            }

            return false;
        } else{

            throw new UnauthorizedSpotifySessionAccessException("User not authorized");
        }
    }
}
