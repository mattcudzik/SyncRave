package org.mcudzik.backend.repository;

import org.mcudzik.backend.model.AuthUser;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthUserRepository extends CrudRepository<AuthUser, UUID> {

    AuthUser findBySpotifyId(String spotifyId);

}
