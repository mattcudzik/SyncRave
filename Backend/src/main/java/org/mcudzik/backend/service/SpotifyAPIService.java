package org.mcudzik.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.hc.core5.http.ParseException;
import org.mcudzik.backend.model.AuthUser;
import org.mcudzik.backend.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

import java.io.IOException;
import java.util.Date;


@Log
@Service
@RequiredArgsConstructor
public class SpotifyAPIService implements ISpotifyAPIService {
    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    @Value("${spotify.authorize.callbackUrl.suffix}")
    private String callbackUrl;

    @Value("${base.address}")
    private String baseAddress;

    private final AuthUserRepository authUserRepository;

    private static SpotifyApi spotifyApi = null;

    private final String scope = "user-read-playback-state user-modify-playback-state user-read-currently-playing playlist-modify-public playlist-modify-private";

    @Override
    public SpotifyApi getSpotifyApi(AuthUser owner) {

        if(spotifyApi == null){
            spotifyApi = new SpotifyApi.Builder()
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .setRedirectUri(SpotifyHttpManager.makeUri(baseAddress + callbackUrl))
                    //.setRefreshToken(owner.getRefreshToken())
                    .build();
        }

        spotifyApi.setRefreshToken(owner.getRefreshToken());
        //additional time (30s) so the token doesn't expire right before making the request
        long additionalTime = 30_000;
        if(owner.getTokenExpiration().getTime() - additionalTime < System.currentTimeMillis()){

            final AuthorizationCodeRefreshRequest request = spotifyApi.authorizationCodeRefresh().build();

            try{
                lock.lock();
                final AuthorizationCodeCredentials credentials = request.execute();
                lock.unlock();

                //calculate token expiration date
                owner.setTokenExpiration(new Date(System.currentTimeMillis() + credentials.getExpiresIn() * 1000));
                owner.setAccessToken(credentials.getAccessToken());
                authUserRepository.save(owner);

                spotifyApi.setAccessToken(credentials.getAccessToken());

            } catch (IOException | ParseException | SpotifyWebApiException e) {
                lock.unlock();
                throw new RuntimeException(e);
            }
        }
        else {
            spotifyApi.setAccessToken(owner.getAccessToken());
        }

        return spotifyApi;
    }

    @Override
    public SpotifyApi getAnonymousSpotifyApi() {
        if(spotifyApi == null){
            spotifyApi = new SpotifyApi.Builder()
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .setRedirectUri(SpotifyHttpManager.makeUri(baseAddress + callbackUrl))
                    .build();
        }

        final ClientCredentialsRequest request = spotifyApi.clientCredentials().build();
        try{
            final ClientCredentials credentials = request.execute();
            spotifyApi.setAccessToken(credentials.getAccessToken());
        }
        catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new RuntimeException(e);
        }

        return spotifyApi;
    }

    @Override
    public String getAuthorizationUrl() {
        final SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(SpotifyHttpManager.makeUri(baseAddress + callbackUrl))
                .build();

        final AuthorizationCodeUriRequest request = spotifyApi.authorizationCodeUri()
                .scope(scope)
                .show_dialog(true)
                .build();

        return request.execute().toString();
    }
}
