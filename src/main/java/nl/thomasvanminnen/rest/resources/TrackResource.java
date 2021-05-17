package nl.thomasvanminnen.rest.resources;

import nl.thomasvanminnen.service.interfaces.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {
    private TrackService trackService;

    public TrackResource() {
    }

    @Inject
    public TrackResource(TrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTracksThatAreNotInPlaylist(@QueryParam("token") String token, @QueryParam("forPlaylist") int playlistId) {
        var allTracksNotInPlaylist = trackService.getAllTracksThatAreNotInPlaylist(playlistId);
        return Response.ok(allTracksNotInPlaylist).build();
    }
}
