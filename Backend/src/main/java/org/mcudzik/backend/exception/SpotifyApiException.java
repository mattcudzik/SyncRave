package org.mcudzik.backend.exception;

public class SpotifyApiException extends RuntimeException {
    public SpotifyApiException(String msg) {
        super(msg);
    }
}
