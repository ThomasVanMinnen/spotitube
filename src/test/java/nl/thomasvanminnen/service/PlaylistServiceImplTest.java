package nl.thomasvanminnen.service;

import nl.thomasvanminnen.persistence.interfaces.PlaylistDAO;
import nl.thomasvanminnen.rest.dtos.PlaylistDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PlaylistServiceImplTest {
    private PlaylistServiceImpl sut;
    private PlaylistDAO playlistDaoMock;

    @BeforeEach
    void setUp() {
        this.playlistDaoMock = Mockito.mock(PlaylistDAO.class);
        this.sut = new PlaylistServiceImpl(playlistDaoMock);
    }

    @Test
    void returnTrueWhenPlaylistIsInserted() {
        PlaylistDTO playlistDTO = new PlaylistDTO(1, "Leuke muziek", true);
        when(playlistDaoMock.insertNewPlaylist(playlistDTO, "")).thenReturn(true);
        Boolean actualResult = sut.insertNewPlaylist(playlistDTO, "");
        assertEquals(true, actualResult);
    }

    @Test
    void returnTrueWhenPlaylistIsUpdated() {
        PlaylistDTO playlistDTO = new PlaylistDTO(1, "Leuke muziek", true);
        when(playlistDaoMock.updatePlaylistWithId(playlistDTO, "", 1)).thenReturn(true);
        Boolean actualResult = sut.updatePlaylistWithId(playlistDTO, "", 1);
        assertEquals(true, actualResult);
    }

    @Test
    void returnTrueWhenPlaylistIsDeleted() {
        when(playlistDaoMock.deletePlaylistWithId(1, "")).thenReturn(true);
        Boolean actualResult = sut.deletePlaylistWithId(1, "");
        assertEquals(true, actualResult);
    }

    @Test
    void returnMapWithPlaylistsAndLength() {
        List<PlaylistDTO> playlistDTOS = new ArrayList<>();
        playlistDTOS.add(new PlaylistDTO(1, "afspeellijst", true));

        Map<String, Object> map = new HashMap<>();
        map.put("playlists", playlistDTOS);
        map.put("length", 420);

        when(playlistDaoMock.getAllPlaylists("")).thenReturn(playlistDTOS);
        when(playlistDaoMock.totalPlaylistsLength()).thenReturn(420);

        Map<String, Object> actualResult = sut.getAllPlaylists("");

        assertEquals(map, actualResult);
    }

    @Test
    void returnTotalPlaylistsLength() {
        when(playlistDaoMock.totalPlaylistsLength()).thenReturn(420);
        int actualResult = sut.totalPlaylistsLength();
        assertEquals(420, actualResult);
    }
}
