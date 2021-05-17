package nl.thomasvanminnen.rest.resources;

import nl.thomasvanminnen.service.interfaces.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class TrackResourceTest {
    private TrackResource sut = new TrackResource();
    private TrackService trackServiceMock;

    @BeforeEach
    void setUp() {
        trackServiceMock = Mockito.mock(TrackService.class);
        sut = new TrackResource(trackServiceMock);
    }

    @Test
    void get200FromGettingAllTracksThatAreNotInPlaylist() {
        Response actualResponse = sut.getAllTracksThatAreNotInPlaylist("token", 1);

        verify(trackServiceMock).getAllTracksThatAreNotInPlaylist(1);
        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }
}
