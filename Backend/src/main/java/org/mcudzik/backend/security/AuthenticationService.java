package org.mcudzik.backend.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.hc.core5.http.ParseException;
import org.mcudzik.backend.model.AnonUser;
import org.mcudzik.backend.model.AuthUser;
import org.mcudzik.backend.model.dto.GuestAuthRequestDTO;
import org.mcudzik.backend.repository.AnonUserRepository;
import org.mcudzik.backend.repository.AuthUserRepository;
import org.mcudzik.backend.repository.SessionRepository;
import org.mcudzik.backend.security.JwtService;
import org.mcudzik.backend.service.ISpotifyAPIService;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

import java.io.IOException;
import java.util.Date;

@Log
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthUserRepository authUserRepository;
    private final AnonUserRepository anonUserRepository;
    private final SessionRepository sessionRepository;
    private final JwtService jwtService;
    private final ISpotifyAPIService spotifyAPIService;

    public String authorizeGuest(GuestAuthRequestDTO guestAuthRequestDTO) {

        var session = sessionRepository.findByInvitationCodeIgnoreCase(guestAuthRequestDTO.getInvitationCode()).orElseThrow(() -> new RuntimeException("Session not found"));

        if(session.getAnonUsers().stream().anyMatch(anonUser -> anonUser.getNick().equalsIgnoreCase(guestAuthRequestDTO.getNickname()))){
            throw new RuntimeException("User already exists");
        }

        if(session.getSessionProperties().getMaxGuests() <= session.getAnonUsers().size()){
            throw new RuntimeException("Session is already full");
        }

        var anonUser = AnonUser.builder()
                .nick(guestAuthRequestDTO.getNickname())
                .session(session)
                .build();

        anonUserRepository.save(anonUser);

        return jwtService.generateToken(anonUser);
    }

    public String authorizeSpotifyUser(String code){
        if(code == null){
            throw new IllegalArgumentException("code is null");
        }

        if(code.length() == 0){
            throw new IllegalArgumentException("code is empty");
        }

        SpotifyApi spotifyApi = spotifyAPIService.getAnonymousSpotifyApi();
        final AuthorizationCodeRequest request = spotifyApi.authorizationCode(code).build();

        try{
            final AuthorizationCodeCredentials credentials = request.execute();
            final long currentTime = System.currentTimeMillis();
            spotifyApi.setAccessToken(credentials.getAccessToken());

            final GetCurrentUsersProfileRequest profileRequest = spotifyApi.getCurrentUsersProfile().build();
            final User profileResponse = profileRequest.execute();

            AuthUser user = authUserRepository.findBySpotifyId(profileResponse.getId());

            //user already exists return token
            if(user != null){
                user.setAccessToken(credentials.getAccessToken());
                user.setTokenExpiration(new Date(currentTime + (credentials.getExpiresIn() * 1000)));
            }
            //user does not exist create new user
            else {
                user = AuthUser.builder()
                        .spotifyId(profileResponse.getId())
                        .spotifyName(profileResponse.getDisplayName())
                        .accessToken(credentials.getAccessToken())
                        .refreshToken(credentials.getRefreshToken())
                        .tokenExpiration(new Date(currentTime + (credentials.getExpiresIn() * 1000)))
                        .build();

                authUserRepository.save(user);
            }

            return jwtService.generateToken(user);
        }
        catch (IOException | ParseException | SpotifyWebApiException e) {
            log.warning(e.getMessage());
            throw new RuntimeException("Could not authorize user", e);
        }
    }
}
