package nl.thomasvanminnen.rest.resources;

import nl.thomasvanminnen.rest.dtos.PlaylistDTO;
import nl.thomasvanminnen.rest.dtos.PostTrackAddToPlaylistDTO;
import nl.thomasvanminnen.service.interfaces.PlaylistService;
import nl.thomasvanminnen.service.interfaces.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/playlists")
public class PlaylistResource {
    private PlaylistService playlistService;
    private TrackService trackService;

    public PlaylistResource() {
    }

    @Inject
    public PlaylistResource(PlaylistService playlistService, TrackService trackService) {
        this.playlistService = playlistService;
        this.trackService = trackService;
    }

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylist(@QueryParam("token") String token) {
        var allPlaylists = playlistService.getAllPlaylists(token);
        return Response.ok(allPlaylists).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertPlaylist(@QueryParam("token") String token, PlaylistDTO playlist) {
        playlistService.insertNewPlaylist(playlist, token);
        var allPlaylists = playlistService.getAllPlaylists(token);
        return Response.created(URI.create("")).entity(allPlaylists).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlaylist(@QueryParam("token") String token, @PathParam("id") int id, PlaylistDTO playlist) {
        playlistService.updatePlaylistWithId(playlist, token, id);
        var allPlaylists = playlistService.getAllPlaylists(token);
        return Response.ok(allPlaylists).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        playlistService.deletePlaylistWithId(id, token);
        var allPlaylists = playlistService.getAllPlaylists(token);
        return Response.ok(allPlaylists).build();
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId, @QueryParam("token") String token) {
        trackService.removeTrackFromPlaylistWithId(trackId, playlistId);
        var tracks = trackService.getAllTracksByPlaylistId(playlistId);
        return Response.ok(tracks).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId) {
        var tracks = trackService.getAllTracksByPlaylistId(playlistId);
        return Response.ok(tracks).build();
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, final PostTrackAddToPlaylistDTO request) {
        trackService.addNewTrackToPlaylist(playlistId, request.id, request.offlineAvailable);
        var tracks = trackService.getAllTracksByPlaylistId(playlistId);
        return Response.created(URI.create("")).entity(tracks).build();
    }
}
