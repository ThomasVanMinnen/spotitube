package nl.thomasvanminnen.service;

import nl.thomasvanminnen.persistence.interfaces.TrackDAO;
import nl.thomasvanminnen.rest.dtos.TrackDTO;
import nl.thomasvanminnen.service.interfaces.TrackService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Default
public class TrackServiceImpl implements TrackService {
    private final TrackDAO trackDAO;

    @Inject
    public TrackServiceImpl(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Override
    public Map<String, Object> getAllTracksByPlaylistId(int playlistId){
        Map<String, Object> map = new HashMap<>();
        List<TrackDTO> trackDTOS = trackDAO.getAllTracksFromPlaylist(playlistId);
        map.put("tracks", trackDTOS);
        return map;
    }

    @Override
    public Map<String, Object> getAllTracksThatAreNotInPlaylist(int playlistId) {
        Map<String, Object> map = new HashMap<>();
        List<TrackDTO> trackDTOS = trackDAO.getAllTracksThatAreNotInPlaylist(playlistId);
        map.put("tracks", trackDTOS);
        return map;
    }

    @Override
    public Boolean removeTrackFromPlaylistWithId(int trackId, int playlistId) {
        return trackDAO.removeTrackFromPlaylistWithId(trackId, playlistId);
    }

    @Override
    public Boolean addNewTrackToPlaylist(int playlistId, int trackId, Boolean offlineAvailable) {
        return trackDAO.insertNewTrack(playlistId, trackId, offlineAvailable);
    }
}
