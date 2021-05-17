package nl.thomasvanminnen.service.interfaces;

import nl.thomasvanminnen.rest.dtos.PlaylistDTO;

import java.util.Map;

public interface PlaylistService {
    Boolean insertNewPlaylist(PlaylistDTO playlist, String token);

    Boolean updatePlaylistWithId(PlaylistDTO playlist, String token, int id);

    Boolean deletePlaylistWithId(int id, String token);

    Map<String, Object> getAllPlaylists(String token);

    int totalPlaylistsLength();
}
