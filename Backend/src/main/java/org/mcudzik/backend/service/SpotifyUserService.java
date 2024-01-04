package org.mcudzik.backend.service;

import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.mcudzik.backend.exception.SpotifyApiException;
import org.mcudzik.backend.model.dto.CurrentPlayerStateDTO;
import org.mcudzik.backend.model.dto.SpotifyUserDTO;
import org.mcudzik.backend.repository.AuthUserRepository;
import org.mcudzik.backend.util.ObjectMapper;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.requests.data.player.*;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SpotifyUserService implements ISpotifyUserService {
    private final ISpotifyAPIService spotifyAPIService;

    private final AuthUserRepository authUserRepository;

    public SpotifyUserDTO getSpotifyUser(UUID ownerId) {
        var owner = authUserRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User not found"));
        var spotify = spotifyAPIService.getSpotifyApi(owner);

        try{
            var request = spotify.getUsersProfile(owner.getSpotifyId()).build();
            spotifyAPIService.lock.lock();
            var profile = request.execute();
            spotifyAPIService.lock.unlock();

            return ObjectMapper.toSpotifyUserDTO(profile);
        }
        catch (IOException | ParseException | SpotifyWebApiException e) {
            spotifyAPIService.lock.unlock();
            throw new SpotifyApiException(e.getMessage());
        }
    }


    public void pauseSong(UUID ownerId) {
        var owner = authUserRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User not found"));
        var spotify = spotifyAPIService.getSpotifyApi(owner);

        try{
            PauseUsersPlaybackRequest request = spotify.pauseUsersPlayback().build();
            spotifyAPIService.lock.lock();
            request.execute();
            spotifyAPIService.lock.unlock();
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            spotifyAPIService.lock.unlock();
            throw new SpotifyApiException(e.getMessage());
        }

    }


    public void playSong(UUID ownerId) {
        var owner = authUserRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User not found"));
        var spotify = spotifyAPIService.getSpotifyApi(owner);

        try{
            StartResumeUsersPlaybackRequest request = spotify.startResumeUsersPlayback().build();
            spotifyAPIService.lock.lock();
            request.execute();
            spotifyAPIService.lock.lock();
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            spotifyAPIService.lock.unlock();
            throw new SpotifyApiException(e.getMessage());
        }
    }


    public void previousSong(UUID ownerId) {
        var owner = authUserRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User not found"));
        var spotify = spotifyAPIService.getSpotifyApi(owner);

        try{
            SkipUsersPlaybackToPreviousTrackRequest request = spotify.skipUsersPlaybackToPreviousTrack().build();
            spotifyAPIService.lock.lock();
            request.execute();
            spotifyAPIService.lock.unlock();
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            spotifyAPIService.lock.unlock();
            throw new SpotifyApiException(e.getMessage());
        }
    }


    public void setVolume(UUID ownerId, Integer volumePercentage) {
        var owner = authUserRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User not found"));
        var spotify = spotifyAPIService.getSpotifyApi(owner);

        try{
            SetVolumeForUsersPlaybackRequest request = spotify.setVolumeForUsersPlayback(volumePercentage).build();
            spotifyAPIService.lock.lock();
            request.execute();
            spotifyAPIService.lock.unlock();
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            spotifyAPIService.lock.unlock();
            throw new SpotifyApiException(e.getMessage());
        }
    }


    public void skipSong(UUID ownerId) {
        var owner = authUserRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User not found"));
        var spotify = spotifyAPIService.getSpotifyApi(owner);

        try{
            SkipUsersPlaybackToNextTrackRequest request = spotify.skipUsersPlaybackToNextTrack().build();
            spotifyAPIService.lock.lock();
            request.execute();
            spotifyAPIService.lock.unlock();
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            spotifyAPIService.lock.unlock();
            throw new SpotifyApiException(e.getMessage());
        }
    }


    public CurrentPlayerStateDTO getState(UUID ownerId) {
        var owner = authUserRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User not found"));

        try{

            var spotify = spotifyAPIService.getSpotifyApi(owner);
            final GetUsersCurrentlyPlayingTrackRequest request = spotify.getUsersCurrentlyPlayingTrack().build();
            spotifyAPIService.lock.lock();
            CurrentlyPlaying currentlyPlaying = request.execute();
            spotifyAPIService.lock.unlock();

            return ObjectMapper.toCurrentPlayerStateDTO(currentlyPlaying);
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            spotifyAPIService.lock.unlock();
            throw new SpotifyApiException(e.getMessage());
        }
    }
}
