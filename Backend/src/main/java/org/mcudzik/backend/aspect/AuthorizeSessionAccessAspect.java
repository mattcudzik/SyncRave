package org.mcudzik.backend.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.mcudzik.backend.security.AuthorizationService;
import org.mcudzik.backend.exception.UnauthorizedSpotifySessionAccessException;
import org.mcudzik.backend.security.JwtService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.UUID;

@RequiredArgsConstructor
@Log
@Aspect
@Component
public class AuthorizeSessionAccessAspect {
    private final AuthorizationService authorizationService;
    private final NativeWebRequest nativeWebRequest;
    private final JwtService jwtService;

    @Around("@annotation(org.mcudzik.backend.aspect.AuthorizeSessionAccess)")
    public Object authorizeSessionAccess(ProceedingJoinPoint joinPoint) throws Throwable {

        var header = nativeWebRequest.getHeader("Authorization");
        if(header == null){
            throw new UnauthorizedSpotifySessionAccessException("Authorization header not found");
        }

        String token = header.substring(7);
        UUID userId = UUID.fromString(jwtService.extractUserId(token));

        UUID sessionId = null;

        var arguments = joinPoint.getArgs();
        for(var argument : arguments) {
            if(argument instanceof UUID){
                sessionId = (UUID) argument;
                break;
            }
        }

        if(sessionId == null){
            throw new UnauthorizedSpotifySessionAccessException("Session id not found");
        }

        if(authorizationService.validateSessionUserAccess(userId, sessionId)){
            return joinPoint.proceed();
        } else {
            throw new UnauthorizedSpotifySessionAccessException("User not authorized to access session");
        }
    }
}
