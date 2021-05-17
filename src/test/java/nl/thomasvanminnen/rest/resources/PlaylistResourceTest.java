package nl.thomasvanminnen.rest.resources;

import nl.thomasvanminnen.rest.dtos.PlaylistDTO;
import nl.thomasvanminnen.rest.dtos.PostTrackAddToPlaylistDTO;
import nl.thomasvanminnen.service.interfaces.PlaylistService;
import nl.thomasvanminnen.service.interfaces.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class PlaylistResourceTest {
    private PlaylistResource sut = new PlaylistResource();
    private PlaylistService playlistServiceMock;
    private TrackService trackServiceMock;

    @BeforeEach
    void setUp() {
        this.playlistServiceMock = Mockito.mock(PlaylistService.class);
        this.trackServiceMock = Mockito.mock(TrackService.class);
        this.sut = new PlaylistResource(playlistServiceMock, trackServiceMock);
    }

    @Test
    void get200FromGettingAllPlaylists() {
        Response actualResponse = sut.getPlaylist("");
        verify(playlistServiceMock).getAllPlaylists("");
        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void get201FromInsertingPlaylists() {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setId(1);
        playlistDTO.setName("Nieuwe afspeellijst");
        playlistDTO.setOwner(true);

        Response actualResponse = sut.insertPlaylist("f0baa020-a770-475f-9b97-e7e280e85d43", playlistDTO);
        verify(playlistServiceMock).getAllPlaylists("f0baa020-a770-475f-9b97-e7e280e85d43");
        assertEquals(Response.Status.CREATED.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void get200FromUpdatingPlaylists() {
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setId(1);
        playlistDTO.setName("Nieuwe afspeellijst naam");
        playlistDTO.setOwner(true);

        Response actualResponse = sut.updatePlaylist("f0baa020-a770-475f-9b97-e7e280e85d43", 1, playlistDTO);
        verify(playlistServiceMock).updatePlaylistWithId(playlistDTO, "f0baa020-a770-475f-9b97-e7e280e85d43", 1);
        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void get200FromDeletingPlaylists() {
        Response actualResponse = sut.deletePlaylist(1, "f0baa020-a770-475f-9b97-e7e280e85d43");
        verify(playlistServiceMock).deletePlaylistWithId( 1, "f0baa020-a770-475f-9b97-e7e280e85d43");
        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void get200FromDeletingTrackFromPlaylists() {
        Response actualResponse = sut.removeTrackFromPlaylist(1, 1, "f0baa020-a770-475f-9b97-e7e280e85d43");
        verify(trackServiceMock).removeTrackFromPlaylistWithId( 1, 1);
        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void get200FromGettingAllTrackFromPlaylists() {
        Response actualResponse = sut.getTracksFromPlaylist( "f0baa020-a770-475f-9b97-e7e280e85d43", 1);
        verify(trackServiceMock).getAllTracksByPlaylistId( 1);
        assertEquals(Response.Status.OK.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void get201FromInsertingNewTrackToPlaylists() {
        PostTrackAddToPlaylistDTO postTrackAddToPlaylist = new PostTrackAddToPlaylistDTO();
        postTrackAddToPlaylist.setId(1);
        postTrackAddToPlaylist.setOfflineAvailable(true);

        Response actualResponse = sut.addNewTrackToPlaylist("f0baa020-a770-475f-9b97-e7e280e85d43",1, postTrackAddToPlaylist);
        verify(trackServiceMock).addNewTrackToPlaylist(1, 1, true);
        assertEquals(Response.Status.CREATED.getStatusCode(), actualResponse.getStatus());
    }
}
