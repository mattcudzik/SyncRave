package org.mcudzik.backend.util;

import com.google.zxing.WriterException;
import org.mcudzik.backend.model.AnonUser;
import org.mcudzik.backend.model.Session;
import org.mcudzik.backend.model.SessionProperties;
import org.mcudzik.backend.model.dto.*;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.model_objects.specification.*;

import java.io.IOException;
import java.util.ArrayList;

public class ObjectMapper {

    public static GetSessionDTO toGetSessionDTO(Session session) {

        GetSessionDTO sessionDTO = new GetSessionDTO();
        sessionDTO.setId(session.getId());
        sessionDTO.setName(session.getName());
        sessionDTO.setCreated(session.getCreated());

        var guests = new ArrayList<GetGuestDTO>();
        for(var guest : session.getAnonUsers()) {
            guests.add(ObjectMapper.toGetGuestDTO(guest));
        }
        sessionDTO.setGuests(guests);

        var sessionInvitation = new SessionInvitationDTO();
        sessionInvitation.setCode(session.getInvitationCode());
        try{
            sessionInvitation.setQrCode(CodeGenerationUtil.generateQRCodeFromCode(session.getInvitationCode()));
        } catch (IOException | WriterException e) {
            sessionInvitation.setQrCode(new byte[]{});
        }

        sessionDTO.setSessionInvitation(sessionInvitation);
        sessionDTO.setProperties(ObjectMapper.toSessionPropertiesDTO(session.getSessionProperties()));

        return sessionDTO;
    }

    public static SessionPropertiesDTO toSessionPropertiesDTO(SessionProperties sessionProperties) {
        SessionPropertiesDTO sessionPropertiesDTO = new SessionPropertiesDTO();

        sessionPropertiesDTO.setMaxSongsPerGuest(sessionProperties.getMaxSongsPerGuest());
        sessionPropertiesDTO.setBanExplicitContent(sessionProperties.getBanExplicit());
        sessionPropertiesDTO.setMaxNumberOfGuests(sessionProperties.getMaxGuests());
        sessionPropertiesDTO.setGeneratePlaylist(sessionProperties.getGeneratePlaylist());

        sessionPropertiesDTO.setBannedGenres(sessionProperties.getBannedGenres());


        return sessionPropertiesDTO;
    }

    public static GetGuestDTO toGetGuestDTO(AnonUser anonUser) {

        GetGuestDTO guestDTO = new GetGuestDTO();
        guestDTO.setNickname(anonUser.getNick());
        guestDTO.setNumSongsAdded(anonUser.getNumSongsAdded());
        return guestDTO;
    }

    public static CurrentPlayerStateDTO toCurrentPlayerStateDTO(CurrentlyPlaying currentlyPlaying) throws SpotifyWebApiException {
        CurrentPlayerStateDTO playerState = new CurrentPlayerStateDTO();

        if(currentlyPlaying == null){
            playerState.setIsPlaying(false);
            return playerState;
        }

        if(currentlyPlaying.getItem().getType() != ModelObjectType.TRACK){
            throw new SpotifyWebApiException("Currently playing object is not a track");
        }


        playerState.setIsPlaying(currentlyPlaying.getIs_playing());
        playerState.setProgressMs(currentlyPlaying.getProgress_ms());
        playerState.setTimestamp(currentlyPlaying.getTimestamp());


        Track track = (Track) currentlyPlaying.getItem();
        playerState.setItem(toTrackObjectDTO(track));

        return playerState;
    }

    public static TrackObjectDTO toTrackObjectDTO(Track track) {
        TrackObjectDTO trackObject = new TrackObjectDTO();

        trackObject.setDurationMs(track.getDurationMs());
        trackObject.setUri(track.getUri());
        trackObject.setName(track.getName());
        trackObject.setId(track.getId());
        trackObject.setHref(track.getHref());
        trackObject.setUrl(track.getExternalUrls().get("spotify"));

        var artists = new ArrayList<ArtistObjectDTO>();
        for (var artist : track.getArtists()) {
            artists.add(ObjectMapper.toArtistObjectDTO(artist));
        }

        trackObject.setArtists(artists);

        trackObject.setAlbum(toAlbumObjectDTO(track.getAlbum()));
        return trackObject;
    }

    public static AlbumObjectDTO toAlbumObjectDTO(AlbumSimplified albumSimplified) {
        AlbumObjectDTO albumObject = new AlbumObjectDTO();
        albumObject.setId(albumSimplified.getId());
        albumObject.setName(albumSimplified.getName());
        albumObject.setReleaseDate(albumSimplified.getReleaseDate());
        albumObject.setUri(albumSimplified.getUri());

        var artists = new ArrayList<ArtistObjectDTO>();
        for (var artist : albumSimplified.getArtists()) {
            artists.add(ObjectMapper.toArtistObjectDTO(artist));
        }
        albumObject.setArtists(artists);

        var images = new ArrayList<ImageObjectDTO>();
        for (var image : albumSimplified.getImages()) {
            images.add(ObjectMapper.toImageObjectDTO(image));
        }
        albumObject.setImages(images);

        return albumObject;
    }

    public static ArtistObjectDTO toArtistObjectDTO(ArtistSimplified artist) {
        ArtistObjectDTO artistObject = new ArtistObjectDTO();
        artistObject.setId(artist.getId());
        artistObject.setName(artist.getName());
        artistObject.setUri(artist.getUri());
        artistObject.setHref(artist.getHref());

        return artistObject;
    }

    public static ImageObjectDTO toImageObjectDTO(Image image) {
        ImageObjectDTO imageObject = new ImageObjectDTO();
        imageObject.setWidth(image.getWidth());
        imageObject.setHeight(image.getHeight());
        imageObject.setUrl(image.getUrl());
        return imageObject;
    }

    public static SessionProperties toSessionProperties(SessionPropertiesDTO sessionPropertiesDTO) {
        SessionProperties sessionProperties = new SessionProperties();

        var defValues = new SessionPropertiesDTO();

        sessionProperties.setBannedGenres(sessionPropertiesDTO.getBannedGenres() == null ? defValues.getBannedGenres() : sessionPropertiesDTO.getBannedGenres());
        sessionProperties.setMaxSongsPerGuest(sessionPropertiesDTO.getMaxSongsPerGuest() == null ? defValues.getMaxSongsPerGuest() : sessionPropertiesDTO.getMaxSongsPerGuest());
        sessionProperties.setMaxGuests(sessionPropertiesDTO.getMaxNumberOfGuests() == null ? defValues.getMaxNumberOfGuests() : sessionPropertiesDTO.getMaxNumberOfGuests());
        sessionProperties.setBanExplicit(sessionPropertiesDTO.getBanExplicitContent() == null ? defValues.getBanExplicitContent() : sessionPropertiesDTO.getBanExplicitContent());
        sessionProperties.setGeneratePlaylist(sessionPropertiesDTO.getGeneratePlaylist() == null ? defValues.getGeneratePlaylist() : sessionPropertiesDTO.getGeneratePlaylist());

        return sessionProperties;
    }

    public static SpotifyUserDTO toSpotifyUserDTO(User user){
        SpotifyUserDTO spotifyUserDTO = new SpotifyUserDTO();

        spotifyUserDTO.setHref(user.getHref());
        spotifyUserDTO.setId(user.getId());
        spotifyUserDTO.setUri(user.getUri());
        spotifyUserDTO.setDisplayName(user.getDisplayName());

        var images = new ArrayList<ImageObjectDTO>();
        for (var image : user.getImages()) {
            images.add(ObjectMapper.toImageObjectDTO(image));
        }

        spotifyUserDTO.setImages(images);

        return spotifyUserDTO;
    }
}
