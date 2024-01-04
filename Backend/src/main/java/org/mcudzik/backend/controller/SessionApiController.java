package org.mcudzik.backend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.mcudzik.backend.aspect.AuthorizeSessionAccess;
import org.mcudzik.backend.service.ISessionService;
import org.mcudzik.backend.exception.UnauthorizedSpotifySessionAccessException;
import org.mcudzik.backend.model.dto.*;
import org.mcudzik.backend.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;


import java.util.List;
import java.util.UUID;


@Log
@RequiredArgsConstructor
@RestController
public class SessionApiController implements SessionApi {


    private final NativeWebRequest nativeWebRequest;
    private final ISessionService sessionService;
    private final JwtService jwtService;

    @Override
    public ResponseEntity<GetSessionDTO> createSession(AddSessionDTO addSessionDTO) {
        var userId = getUserIdFromHeader();

        var response = sessionService.createSession(userId, addSessionDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<GetSessionDTO>> getSesssions() {
        return new ResponseEntity<>(sessionService.getOwnersSessions(getUserIdFromHeader()), HttpStatus.OK);
    }

    @AuthorizeSessionAccess
    @Override
    public ResponseEntity<Void> addSongToQueue(UUID sessionId, AddSongDTO addSongDTO) {
        sessionService.addSongToSession(sessionId, addSongDTO, getUserIdFromHeader());

        return ResponseEntity.ok().build();
    }

    @AuthorizeSessionAccess
    @Override
    public ResponseEntity<CurrentPlayerStateDTO> getCurrentSong(UUID sessionId) {
        return new ResponseEntity<>(sessionService.getCurrentSong(sessionId), HttpStatus.OK);
    }


    @AuthorizeSessionAccess
    @Override
    public ResponseEntity<List<TrackObjectDTO>> getSessionQueue(UUID sessionId) {
        var response = sessionService.getCurrentQueue(sessionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @AuthorizeSessionAccess
    @Override
    public ResponseEntity<SessionPropertiesDTO> setSessionProperties(UUID sessionId, SessionPropertiesDTO sessionPropertiesDTO) {

        return new ResponseEntity<>(sessionService.updateSessionProperties(sessionId, sessionPropertiesDTO), HttpStatus.OK);
    }

    @AuthorizeSessionAccess
    @Override
    public ResponseEntity<Void> deleteSesssionById(UUID sessionId) {
        sessionService.deleteSession(sessionId);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @AuthorizeSessionAccess
    @Override
    public ResponseEntity<String> getSessionPlaylist(UUID sessionId) {
        return new ResponseEntity<>(sessionService.getPlaylistUrl(sessionId), HttpStatus.OK);
    }

    @AuthorizeSessionAccess
    @Override
    public ResponseEntity<GetSessionDTO> getSessionById(UUID sessionId) {
        var response = sessionService.getSessionById(sessionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private UUID getUserIdFromHeader() {
        var header = nativeWebRequest.getHeader("Authorization");
        if(header == null){
            throw new UnauthorizedSpotifySessionAccessException("Authorization header not found");
        }
        var token = header.substring(7);
        return UUID.fromString(jwtService.extractUserId(token));
    }

}
