package org.mcudzik.backend.service;

import org.mcudzik.backend.model.dto.*;

import java.util.List;
import java.util.UUID;

public interface ISessionService {
    public String getInvitationCode(UUID sessionId);

    public List<GetSessionDTO> getOwnersSessions(UUID ownerId);

    public GetSessionDTO getSessionById(UUID sessionId);

    public GetSessionDTO createSession(UUID ownerId, AddSessionDTO addSessionDTO);

    public String getPlaylistUrl(UUID sessionId);

    public void addSongToSession(UUID sessionId, AddSongDTO addSongDTO, UUID userId);

    public CurrentPlayerStateDTO getCurrentSong(UUID sessionId);

    public List<TrackObjectDTO> getCurrentQueue(UUID sessionId);

    public SessionPropertiesDTO updateSessionProperties(UUID sessionId, SessionPropertiesDTO sessionPropertiesDTO);

    public void deleteSession(UUID sessionId);

}
