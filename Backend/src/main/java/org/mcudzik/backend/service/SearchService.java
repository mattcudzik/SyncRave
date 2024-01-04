package org.mcudzik.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.hc.core5.http.ParseException;
import org.mcudzik.backend.exception.SpotifyApiException;
import org.mcudzik.backend.model.dto.TrackObjectDTO;
import org.mcudzik.backend.util.ObjectMapper;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log
@Service
public class SearchService implements ISearchService {
    private final ISpotifyAPIService spotifyAPIService;

    public List<TrackObjectDTO> search(String query, Integer limit, Integer offset) {
        if(query.isBlank()){
            throw new IllegalArgumentException("query can't be empty");
        }

        var spotify = spotifyAPIService.getAnonymousSpotifyApi();
        var request = spotify.searchTracks(query).limit(limit).offset(offset).build();
        try{
            spotifyAPIService.lock.lock();
            var results = request.execute();
            spotifyAPIService.lock.unlock();

            ArrayList<TrackObjectDTO> response = new ArrayList<>();

            for(var result: results.getItems()){
                response.add(ObjectMapper.toTrackObjectDTO(result));
            }

            return response;

        } catch (IOException | ParseException | SpotifyWebApiException e) {
            spotifyAPIService.lock.unlock();
            throw new SpotifyApiException(e.getMessage());
        }

    }
}
