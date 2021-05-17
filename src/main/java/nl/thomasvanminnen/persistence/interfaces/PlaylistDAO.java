package nl.thomasvanminnen.persistence.interfaces;

import nl.thomasvanminnen.rest.dtos.PlaylistDTO;

import java.util.List;

public interface PlaylistDAO {
    List<PlaylistDTO> getAllPlaylists(String token);

    Boolean insertNewPlaylist(PlaylistDTO playlist, String token);

    Boolean updatePlaylistWithId(PlaylistDTO playlist, String token, int id);

    Boolean deletePlaylistWithId(int id, String token);

    int totalPlaylistsLength();
}
