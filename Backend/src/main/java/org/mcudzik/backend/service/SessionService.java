package org.mcudzik.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.hc.core5.http.ParseException;
import org.mcudzik.backend.exception.SpotifyApiException;
import org.mcudzik.backend.model.AnonUser;
import org.mcudzik.backend.model.AuthUser;
import org.mcudzik.backend.model.Session;
import org.mcudzik.backend.model.dto.*;
import org.mcudzik.backend.repository.AnonUserRepository;
import org.mcudzik.backend.repository.AuthUserRepository;
import org.mcudzik.backend.repository.SessionRepository;
import org.mcudzik.backend.util.CodeGenerationUtil;
import org.mcudzik.backend.util.ObjectMapper;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.player.*;

import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;

@RequiredArgsConstructor
@Log
@Service
public class SessionService implements ISessionService {

    private final SessionRepository sessionRepository;
    private final AuthUserRepository authUserRepository;
    private final AnonUserRepository anonUserRepository;
    private final ISpotifyAPIService spotifyAPIService;

    public String getInvitationCode(UUID sessionId){
        return sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found")).getInvitationCode();
    }

    public List<GetSessionDTO> getOwnersSessions(UUID ownerId){
        var sessions = sessionRepository.findByOwner_Id(ownerId);

        var result = new ArrayList<GetSessionDTO>();

        for(var session : sessions){

            var sessionDTO = ObjectMapper.toGetSessionDTO(session);
            //sessionDTO.setCurrentPlayerState(getCurrentSong(session.getId()));
            //sessionDTO.setOwner(ObjectMapper.toSpotifyUserDTO(getSessionOwnerSpotifyData(session.getId())));
            result.add(sessionDTO);
        }

        return result;
    }

    public GetSessionDTO getSessionById(UUID sessionId) {

        var session = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));
        GetSessionDTO response = ObjectMapper.toGetSessionDTO(session);
        response.setOwner(ObjectMapper.toSpotifyUserDTO(getSessionOwnerSpotifyData(sessionId)));
        return response;
    }

    public GetSessionDTO createSession(UUID ownerId, AddSessionDTO addSessionDTO){

        var owner = authUserRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("User not found"));

        boolean exists = sessionRepository.findByOwner_Id(ownerId).stream().anyMatch(session -> session.getName().equals(addSessionDTO.getName()));

        if(exists){
            throw new RuntimeException("Session with this name already exists");
        }

        if(addSessionDTO.getProperties() == null){
            addSessionDTO.setProperties(new SessionPropertiesDTO());
        }

        var session = Session.builder()
                .created(new Date(System.currentTimeMillis()))
                .invitationCode(CodeGenerationUtil.generateRandomCode(6))
                .name(addSessionDTO.getName())
                .sessionProperties(ObjectMapper.toSessionProperties(addSessionDTO.getProperties()))
                .owner(owner)
                .anonUsers(new ArrayList<>())
                .spotifyPlaylistId(addSessionDTO.getProperties().getGeneratePlaylist()? createPlaylist(owner, addSessionDTO.getName()) :  null)
                .tracksAdded(new ArrayList<>())
                .build();

        sessionRepository.save(session);

        var sessionDTO = ObjectMapper.toGetSessionDTO(session);
        sessionDTO.setCurrentPlayerState(getCurrentSong(session.getId()));
        sessionDTO.setOwner(ObjectMapper.toSpotifyUserDTO(getSessionOwnerSpotifyData(session.getId())));

        return sessionDTO;
    }

    public String getPlaylistUrl(UUID sessionId){
        var session = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));

        if(session.getSpotifyPlaylistId() == null){
            throw new RuntimeException("Playlist not generated");
        }
        var spotify = spotifyAPIService.getSpotifyApi(session.getOwner());

        try{
            var request = spotify.getPlaylist(session.getSpotifyPlaylistId()).build();

            spotifyAPIService.lock.lock();
            var url = request.execute().getExternalUrls().get("spotify");
            spotifyAPIService.lock.unlock();

            return url;

        } catch (IOException | ParseException | SpotifyWebApiException e) {
            log.warning(e.getMessage());
            e.printStackTrace();

            throw new SpotifyApiException(e.getMessage());
        }
    }

    public void addSongToSession(UUID sessionId, AddSongDTO addSongDTO, UUID userId){
        var owner = getSessionOwner(sessionId);


        var session = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));
        var properties = session.getSessionProperties();

        AnonUser user = null;

        if(!userId.equals(owner.getId())) {
            user = anonUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("Guest user not found"));

            Integer maxGuestSongs = properties.getMaxSongsPerGuest();

            if(maxGuestSongs != -1 && maxGuestSongs <= user.getNumSongsAdded()){
                throw new RuntimeException("User reached song limit");
            }
        }

        try{
            var spotify = spotifyAPIService.getSpotifyApi(owner);

            spotifyAPIService.lock.lock();
            var track = spotify.getTrack(addSongDTO.getId()).build().execute();
            spotifyAPIService.lock.unlock();

            //check restrictions only for guests
            if(userId != owner.getId()){
                if(properties.getBanExplicit() && track.getIsExplicit()){
                    throw new RuntimeException("Explicit content is banned");
                }

                spotifyAPIService.lock.lock();
                var artist = spotify.getArtist(track.getArtists()[0].getId()).build().execute();
                spotifyAPIService.lock.unlock();

                var genres = artist.getGenres();
                List<String> bannedGenres = properties.getBannedGenres();

                for(var genre: genres){
                    if(bannedGenres.stream().anyMatch(x-> x.equals(genre))){
                        throw new RuntimeException(genre + " is banned");
                    }
                }
            }

            spotifyAPIService.lock.lock();
            spotify.addItemToUsersPlaybackQueue(track.getUri()).build().execute();
            spotifyAPIService.lock.unlock();

            if(user != null){
                anonUserRepository.updateNumSongsAddedById(user.getNumSongsAdded() + 1, user.getId());
            }

            var addedTracks = session.getTracksAdded();

            try{
                if(session.getSessionProperties().getGeneratePlaylist() && !addedTracks.contains(track.getUri())){
                    addedTracks.add(track.getUri());
                    sessionRepository.save(session);

                    var addSongToPlaylistRequest = spotify.addItemsToPlaylist(session.getSpotifyPlaylistId(),new String[]{track.getUri()}).build();
                    spotifyAPIService.lock.lock();
                    addSongToPlaylistRequest.execute();
                    spotifyAPIService.lock.unlock();
                }
            } catch(Exception e){
                spotifyAPIService.lock.unlock();
                log.warning("Couldn't add track to playlist" + e.getMessage());
                e.printStackTrace();
            }



        } catch (IOException | ParseException | SpotifyWebApiException e) {
            spotifyAPIService.lock.lock();
            throw new SpotifyApiException(e.getMessage());
        }
    }

    public CurrentPlayerStateDTO getCurrentSong(UUID sessionId){
        var owner = getSessionOwner(sessionId);

        try {
            var spotify = spotifyAPIService.getSpotifyApi(owner);
            GetUsersCurrentlyPlayingTrackRequest request = spotify.getUsersCurrentlyPlayingTrack().build();

            spotifyAPIService.lock.lock();
            CurrentlyPlaying currentlyPlaying = request.execute();
            spotifyAPIService.lock.unlock();

            return ObjectMapper.toCurrentPlayerStateDTO(currentlyPlaying);

        } catch (IOException | ParseException | SpotifyWebApiException e) {
            spotifyAPIService.lock.unlock();
            log.warning(e.getMessage());
            e.printStackTrace();

            throw new SpotifyApiException(e.getMessage());
        }
    }

    public List<TrackObjectDTO> getCurrentQueue(UUID sessionId){
        var owner = getSessionOwner(sessionId);

        try {
            var spotify = spotifyAPIService.getSpotifyApi(owner);
            GetTheUsersQueueRequest request = spotify.getTheUsersQueue().build();

            ArrayList<TrackObjectDTO> currentQueue = new ArrayList<>();

            spotifyAPIService.lock.lock();
            var queue = request.execute().getQueue();
            spotifyAPIService.lock.unlock();

            for(var queueItem : queue){
                if(queueItem.getType() == ModelObjectType.TRACK) {
                    var track = (Track) queueItem;
                    currentQueue.add(ObjectMapper.toTrackObjectDTO(track));
                }
            }

            return currentQueue;

        } catch (IOException | ParseException | SpotifyWebApiException e) {
            log.warning(e.getMessage());
            e.printStackTrace();

            throw new SpotifyApiException(e.getMessage());
        }
    }

    public SessionPropertiesDTO updateSessionProperties(UUID sessionId, SessionPropertiesDTO sessionPropertiesDTO){
        var session = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));
        var properties = session.getSessionProperties();

        BiFunction<Object, Object, Object> compareValues = (Object newValue, Object oldValue) -> {
            return newValue == null ? oldValue : newValue;
        };

        properties.setMaxSongsPerGuest((Integer) compareValues.apply(sessionPropertiesDTO.getMaxSongsPerGuest(), properties.getMaxSongsPerGuest()));
        properties.setGeneratePlaylist((Boolean) compareValues.apply(sessionPropertiesDTO.getGeneratePlaylist(), properties.getGeneratePlaylist()));
        properties.setBanExplicit((Boolean) compareValues.apply(sessionPropertiesDTO.getBanExplicitContent(), properties.getBanExplicit()));
        properties.setMaxGuests((Integer) compareValues.apply(sessionPropertiesDTO.getMaxNumberOfGuests(), properties.getMaxGuests()));
        properties.setBannedGenres(sessionPropertiesDTO.getBannedGenres());

        sessionRepository.save(session);

        return ObjectMapper.toSessionPropertiesDTO(session.getSessionProperties());
    }

    public void deleteSession(UUID sessionId){
        sessionRepository.deleteById(sessionId);
    }

    private AuthUser getSessionOwner(UUID sessionId){
        var session = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));
        return session.getOwner();
    }

    private User getSessionOwnerSpotifyData(UUID sessionId){
        var session = sessionRepository.findById(sessionId).orElseThrow(() -> new RuntimeException("Session not found"));
        var owner = session.getOwner();

        try{
            var spotify = spotifyAPIService.getSpotifyApi(owner);
            var request = spotify.getUsersProfile(owner.getSpotifyId()).build();

            spotifyAPIService.lock.lock();
            var profile = request.execute();
            spotifyAPIService.lock.unlock();

            return profile;
        }
        catch (IOException | ParseException | SpotifyWebApiException e) {
            spotifyAPIService.lock.unlock();
            throw new SpotifyApiException(e.getMessage());
        }
    }

    private String createPlaylist(AuthUser owner, String playlistName){
        var spotify = spotifyAPIService.getSpotifyApi(owner);

        try{
            var request = spotify.createPlaylist(owner.getSpotifyId(), playlistName).build();

            spotifyAPIService.lock.lock();
            var playlist = request.execute();
            spotifyAPIService.lock.unlock();

            return playlist.getId();
        }
        catch (IOException | ParseException | SpotifyWebApiException e) {
            spotifyAPIService.lock.unlock();
            throw new SpotifyApiException(e.getMessage());
        }
    }

}
