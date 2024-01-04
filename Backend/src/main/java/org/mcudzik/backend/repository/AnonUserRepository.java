package org.mcudzik.backend.repository;

import org.mcudzik.backend.model.AnonUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface AnonUserRepository extends CrudRepository<AnonUser, UUID> {
    @Transactional
    @Modifying
    @Query("update AnonUser a set a.numSongsAdded = ?1 where a.id = ?2")
    int updateNumSongsAddedById(long numSongsAdded, UUID id);

    Optional<AnonUser> findByNick(String nick);
}
