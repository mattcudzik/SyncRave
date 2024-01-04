package org.mcudzik.backend.controller;

import lombok.RequiredArgsConstructor;
import org.mcudzik.backend.service.ISearchService;
import org.mcudzik.backend.model.dto.TrackObjectDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchApiController implements SearchApi {

    private final ISearchService searchService;

    @Override
    public ResponseEntity<List<TrackObjectDTO>> searchSpotify(String q, Integer limit, Integer offset) {
        return new ResponseEntity<>(searchService.search(q, limit, offset), HttpStatus.OK);
    }

}
