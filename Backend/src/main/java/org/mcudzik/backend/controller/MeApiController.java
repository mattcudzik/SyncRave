package org.mcudzik.backend.controller;

import lombok.RequiredArgsConstructor;
import org.mcudzik.backend.service.ISpotifyUserService;
import org.mcudzik.backend.exception.UnauthorizedSpotifySessionAccessException;
import org.mcudzik.backend.model.dto.CurrentPlayerStateDTO;
import org.mcudzik.backend.model.dto.SpotifyUserDTO;
import org.mcudzik.backend.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class MeApiController implements MeApi {

    private final JwtService jwtService;
    private final NativeWebRequest nativeWebRequest;
    private final ISpotifyUserService spotifyUserService;

    private UUID getUserIdFromHeader(){
        String token = nativeWebRequest.getHeader("Authorization");
        if(token == null){
            throw new UnauthorizedSpotifySessionAccessException("Authorization header not found");
        }
        String id = jwtService.extractUserId(token.substring(7));
        return UUID.fromString(id);
    }

    @Override
    public ResponseEntity<SpotifyUserDTO> getSpotifyUser() {
        var owner = getUserIdFromHeader();

        return new ResponseEntity<>(spotifyUserService.getSpotifyUser(owner), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> pauseSong() {
        var owner = getUserIdFromHeader();
        spotifyUserService.pauseSong(owner);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> playSong() {
        var owner = getUserIdFromHeader();
        spotifyUserService.playSong(owner);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> previousSong() {
        var owner = getUserIdFromHeader();
        spotifyUserService.previousSong(owner);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> setVolume(Integer volumePercentage) {
        var owner = getUserIdFromHeader();
        spotifyUserService.setVolume(owner, volumePercentage);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> skipSong() {
        var owner = getUserIdFromHeader();
        spotifyUserService.skipSong(owner);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CurrentPlayerStateDTO> getState() {
        var owner = getUserIdFromHeader();

        return new ResponseEntity<>(spotifyUserService.getState(owner), HttpStatus.OK);
    }
}
