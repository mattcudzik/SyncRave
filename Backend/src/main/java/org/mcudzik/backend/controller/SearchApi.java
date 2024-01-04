/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.mcudzik.backend.controller;

import org.mcudzik.backend.model.dto.TrackObjectDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Tag(name = "search", description = "the search API")
public interface SearchApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /search : search for song
     *
     * @param q Query to search for (required)
     * @param limit Number of results to return (optional, default to 20)
     * @param offset Offset for results (optional, default to 0)
     * @return OK (status code 200)
     */
    @Operation(
        operationId = "searchSpotify",
        summary = "search for song",
        tags = { "search" },
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TrackObjectDTO.class)))
            })
        },
        security = {
            @SecurityRequirement(name = "bearerAuth")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/search",
        produces = { "application/json" }
    )
    default ResponseEntity<List<TrackObjectDTO>> searchSpotify(
        @NotNull @Parameter(name = "q", description = "Query to search for", required = true, in = ParameterIn.QUERY) @Valid @RequestParam(value = "q", required = true) String q,
        @Min(0) @Max(50) @Parameter(name = "limit", description = "Number of results to return", in = ParameterIn.QUERY) @Valid @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
        @Min(0) @Max(1000) @Parameter(name = "offset", description = "Offset for results", in = ParameterIn.QUERY) @Valid @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"duration_ms\" : 2, \"artists\" : [ { \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\", \"uri\" : \"uri\" }, { \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\", \"uri\" : \"uri\" } ], \"album\" : \"\", \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\", \"uri\" : \"uri\", \"url\" : \"url\" }, { \"duration_ms\" : 2, \"artists\" : [ { \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\", \"uri\" : \"uri\" }, { \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\", \"uri\" : \"uri\" } ], \"album\" : \"\", \"name\" : \"name\", \"href\" : \"href\", \"id\" : \"id\", \"uri\" : \"uri\", \"url\" : \"url\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}