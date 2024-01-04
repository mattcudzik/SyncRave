package org.mcudzik.backend.service;
import org.mcudzik.backend.model.dto.TrackObjectDTO;

import java.util.List;

public interface ISearchService {
    public List<TrackObjectDTO> search(String query, Integer limit, Integer offset);
}
