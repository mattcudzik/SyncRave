package org.mcudzik.backend.service;

import org.mcudzik.backend.model.AuthUser;
import se.michaelthelin.spotify.SpotifyApi;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface ISpotifyAPIService {

    public final Lock lock = new ReentrantLock();

    SpotifyApi getSpotifyApi(AuthUser owner);

    SpotifyApi getAnonymousSpotifyApi();

    String getAuthorizationUrl();
}
