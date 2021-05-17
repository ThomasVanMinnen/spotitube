package nl.thomasvanminnen.service;

import nl.thomasvanminnen.persistence.interfaces.PlaylistDAO;
import nl.thomasvanminnen.rest.dtos.PlaylistDTO;
import nl.thomasvanminnen.service.interfaces.PlaylistService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Default
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistDAO playlistDAO;

    @Inject
    public PlaylistServiceImpl(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Override
    public Boolean insertNewPlaylist(PlaylistDTO playlist, String token) {
        return playlistDAO.insertNewPlaylist(playlist, token);
    }

    @Override
    public Boolean updatePlaylistWithId(PlaylistDTO playlist, String token, int id) {
        return playlistDAO.updatePlaylistWithId(playlist, token, id);
    }

    @Override
    public Boolean deletePlaylistWithId(int id, String token) {
        return playlistDAO.deletePlaylistWithId(id, token);
    }

    @Override
    public Map<String, Object> getAllPlaylists(String token) {
        Map<String, Object> map = new HashMap<>();
        List<PlaylistDTO> playlistDTOS = playlistDAO.getAllPlaylists(token);
        int totalPlaylistsLength = playlistDAO.totalPlaylistsLength();
        map.put("playlists", playlistDTOS);
        map.put("length", totalPlaylistsLength);
        return map;
    }

    @Override
    public int totalPlaylistsLength() {
        return playlistDAO.totalPlaylistsLength();
    }
}
