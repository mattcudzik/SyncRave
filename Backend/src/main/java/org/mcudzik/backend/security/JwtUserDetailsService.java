package org.mcudzik.backend.security;


import lombok.RequiredArgsConstructor;
import org.mcudzik.backend.repository.AnonUserRepository;
import org.mcudzik.backend.repository.AuthUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final AnonUserRepository anonUserRepository;
    private final AuthUserRepository authUserRepository;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UUID id = UUID.fromString(userId);

        UserDetails user = authUserRepository.findById(id).orElse(null);
        if(user == null){
            user = anonUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }

        return user;
    }

}
