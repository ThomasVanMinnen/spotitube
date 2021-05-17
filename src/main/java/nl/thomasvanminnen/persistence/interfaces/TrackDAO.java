package nl.thomasvanminnen.persistence.interfaces;

import nl.thomasvanminnen.rest.dtos.TrackDTO;

import java.util.List;

public interface TrackDAO {
    List<TrackDTO> getAllTracksFromPlaylist(int playlistId);

    List<TrackDTO> getAllTracksThatAreNotInPlaylist(int playlistId);

    Boolean removeTrackFromPlaylistWithId(int trackId, int playlistId);

    Boolean insertNewTrack(int playlistId, int trackId, Boolean offlineAvailable);
}
