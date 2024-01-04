package org.mcudzik.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.mcudzik.backend.security.AuthenticationService;
import org.mcudzik.backend.service.ISpotifyAPIService;
import org.mcudzik.backend.model.dto.GetSessionDTO;
import org.mcudzik.backend.model.dto.GuestAuthRequestDTO;
import org.mcudzik.backend.repository.AnonUserRepository;
import org.mcudzik.backend.security.JwtService;
import org.mcudzik.backend.util.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Log
@RestController
@RequiredArgsConstructor
public class AuthApiController implements AuthApi {

    private final ISpotifyAPIService spotifyAPIService;
    private final AuthenticationService authenticationService;

    private final AnonUserRepository anonUserRepository;
    private final JwtService jwtService;


    @Override
    public ResponseEntity<String> authUser() {
        String url = spotifyAPIService.getAuthorizationUrl();

        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GetSessionDTO> authGuest(GuestAuthRequestDTO guestAuthRequestDTO) {

        if (guestAuthRequestDTO.getNickname().isBlank() || guestAuthRequestDTO.getInvitationCode() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var token = authenticationService.authorizeGuest(guestAuthRequestDTO);
        long currentTime = System.currentTimeMillis();
        long expDate = jwtService.extractExpirationDate(token).getTime();
        ResponseCookie cookie = ResponseCookie.from("token", token).sameSite("None").path("/").maxAge((expDate - currentTime)/1000).build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.SET_COOKIE, cookie.toString());
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "SET-COOKIE");

        var userId = jwtService.extractUserId(token);
        var user = anonUserRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new RuntimeException("User not found"));

        var session = ObjectMapper.toGetSessionDTO(user.getSession());

        return new ResponseEntity<>(session, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> callback(String code, String error, String state) {
        if (code == null) {
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        var token = authenticationService.authorizeSpotifyUser(code);

        long currentTime = System.currentTimeMillis();
        long expDate = jwtService.extractExpirationDate(token).getTime();
        ResponseCookie cookie = ResponseCookie.from("token", token).sameSite("None").path("/").secure(true).maxAge((expDate - currentTime)/1000).build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.SET_COOKIE, cookie.toString());
        headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "SET-COOKIE");

        return new ResponseEntity<>(token, headers, HttpStatus.OK);
    }

}