package org.mcudzik.backend.repository;

import org.mcudzik.backend.model.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends CrudRepository<Session, UUID> {
    Optional<Session> findByInvitationCodeIgnoreCase(String invitationCode);

    List<Session> findByOwner_Id(UUID id);

    boolean existsByAnonUsers_NickIgnoreCase(String nick);

}
