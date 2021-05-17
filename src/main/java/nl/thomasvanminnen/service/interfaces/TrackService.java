package nl.thomasvanminnen.service.interfaces;

import java.util.Map;

public interface TrackService {
    Map<String, Object> getAllTracksByPlaylistId(int playlistId);

    Map<String, Object> getAllTracksThatAreNotInPlaylist(int playlistId);

    Boolean removeTrackFromPlaylistWithId(int trackId, int playlistId);

    Boolean addNewTrackToPlaylist(int playlistId, int id, Boolean offlineAvailable);
}
