/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.mcudzik.backend.controller;

import org.mcudzik.backend.model.dto.CurrentPlayerStateDTO;
import org.mcudzik.backend.model.dto.SpotifyUserDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Tag(name = "me", description = "the me API")
public interface MeApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /me : Get Spotify user info
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getSpotifyUser",
        summary = "Get Spotify user info",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = SpotifyUserDTO.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/me",
        produces = { "application/json" }
    )
    default ResponseEntity<SpotifyUserDTO> getSpotifyUser(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"images\" : [ { \"width\" : 300, \"url\" : \"https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228\n\", \"height\" : 300 }, { \"width\" : 300, \"url\" : \"https://i.scdn.co/image/ab67616d00001e02ff9ca10b55ce82ae553c8228\n\", \"height\" : 300 } ], \"href\" : \"href\", \"id\" : \"id\", \"display_name\" : \"display_name\", \"uri\" : \"uri\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /me/player/state : Get player state
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "getState",
        summary = "Get player state",
        tags = { "player" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CurrentPlayerStateDTO.class))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/me/player/state",
        produces = { "application/json" }
    )
    default ResponseEntity<CurrentPlayerStateDTO> getState(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"item\" : { \"duration_ms\" : 2, \"artists\" : [ { \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\", \"uri\" : \"uri\" }, { \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\", \"uri\" : \"uri\" } ], \"album\" : \"\", \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\", \"uri\" : \"uri\", \"url\" : \"url\" }, \"is_playing\" : true, \"progress_ms\" : 5, \"timestamp\" : 5 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /me/player/pause : Pause current song
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "pauseSong",
        summary = "Pause current song",
        tags = { "player" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/me/player/pause"
    )
    default ResponseEntity<Void> pauseSong(
        
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /me/player/play : Start/Resume current song
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "playSong",
        summary = "Start/Resume current song",
        tags = { "player" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/me/player/play"
    )
    default ResponseEntity<Void> playSong(
        
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /me/player/previous : Previous song
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "previousSong",
        summary = "Previous song",
        tags = { "player" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/me/player/previous"
    )
    default ResponseEntity<Void> previousSong(
        
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /me/player/volume : Set playback volume
     *
     * @param volumePercentage  (required)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "setVolume",
        summary = "Set playback volume",
        tags = { "player" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/me/player/volume"
    )
    default ResponseEntity<Void> setVolume(
        @NotNull @Min(0) @Max(100) @Parameter(name = "volume_percentage", description = "", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "volume_percentage", required = true) Integer volumePercentage
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /me/player/next : Skip current song
     *
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "skipSong",
        summary = "Skip current song",
        tags = { "player" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK")
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/me/player/next"
    )
    default ResponseEntity<Void> skipSong(
        
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
