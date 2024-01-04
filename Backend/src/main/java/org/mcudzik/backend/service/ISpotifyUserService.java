package org.mcudzik.backend.service;

import org.mcudzik.backend.model.dto.CurrentPlayerStateDTO;
import org.mcudzik.backend.model.dto.SpotifyUserDTO;

import java.util.UUID;

public interface ISpotifyUserService {
    SpotifyUserDTO getSpotifyUser(UUID ownerId);


    void pauseSong(UUID ownerId);


    void playSong(UUID ownerId);


    void previousSong(UUID ownerId);


    void setVolume(UUID ownerId, Integer volumePercentage);


    void skipSong(UUID ownerId);


    CurrentPlayerStateDTO getState(UUID ownerId);
}
