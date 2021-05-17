package nl.thomasvanminnen.service;

import nl.thomasvanminnen.persistence.interfaces.TrackDAO;
import nl.thomasvanminnen.persistence.interfaces.UserDAO;
import nl.thomasvanminnen.rest.dtos.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TrackServiceImplTest {
    private TrackServiceImpl sut;
    private TrackDAO trackDAOMock;

    @BeforeEach
    void setUp() {
        this.trackDAOMock = Mockito.mock(TrackDAO.class);
        this.sut = new TrackServiceImpl(trackDAOMock);
    }

    @Test
    void returnMapWithTracksFromPlaylist() {
        List<TrackDTO> trackDTOS = new ArrayList<>();
        trackDTOS.add(new TrackDTO());

        Map<String, Object> map = new HashMap<>();
        map.put("tracks", trackDTOS);

        when(trackDAOMock.getAllTracksFromPlaylist(1)).thenReturn(trackDTOS);

        Map<String, Object> actualResult = sut.getAllTracksByPlaylistId(1);
        assertEquals(map, actualResult);
    }

    @Test
    void returnMapWithTracksNotInPlaylist() {
        List<TrackDTO> trackDTOS = new ArrayList<>();
        trackDTOS.add(new TrackDTO());

        Map<String, Object> map = new HashMap<>();
        map.put("tracks", trackDTOS);

        when(trackDAOMock.getAllTracksThatAreNotInPlaylist(1)).thenReturn(trackDTOS);

        Map<String, Object> actualResult = sut.getAllTracksThatAreNotInPlaylist(1);
        assertEquals(map, actualResult);
    }

    @Test
    void returnTrueWhenTrackIsRemovedFromPlaylistWithId() {
        when(trackDAOMock.removeTrackFromPlaylistWithId(1, 1)).thenReturn(true);
        Boolean actualResult = sut.removeTrackFromPlaylistWithId(1, 1);
        verify(trackDAOMock).removeTrackFromPlaylistWithId(1, 1);
        assertEquals(true, actualResult);
    }

    @Test
    void returnTrueWhenTrackIsAddedToPlaylistWithId() {
        when(trackDAOMock.insertNewTrack(1, 1, true)).thenReturn(true);
        Boolean actualResult = sut.addNewTrackToPlaylist(1, 1, true);
        verify(trackDAOMock).insertNewTrack(1, 1, true);
        assertEquals(true, actualResult);
    }
}
