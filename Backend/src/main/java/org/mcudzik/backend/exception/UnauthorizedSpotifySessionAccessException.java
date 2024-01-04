package org.mcudzik.backend.exception;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedSpotifySessionAccessException extends AuthenticationException {
    public UnauthorizedSpotifySessionAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UnauthorizedSpotifySessionAccessException(String msg) {
        super(msg);
    }
}
