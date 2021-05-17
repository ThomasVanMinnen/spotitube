package nl.thomasvanminnen.rest.resources;

import nl.thomasvanminnen.service.interfaces.LoginService;
import nl.thomasvanminnen.rest.dtos.LoginDTO;
import nl.thomasvanminnen.rest.dtos.TokenDTO;
import nl.thomasvanminnen.rest.exceptions.IncorrectUserDetailsException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/login")
public class LoginResource {
    private LoginService loginService;

    public LoginResource() {
    }

    @Inject
    public LoginResource(LoginService loginService) {
        this.loginService = loginService;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDTO loginDTO) {
        TokenDTO tokenDTO = loginService.loginUser(loginDTO);

        if (tokenDTO != null) {
            return Response.created(URI.create("")).entity(tokenDTO).build();
        } else {
            throw new IncorrectUserDetailsException("Gebruiker is niet gevonden");
        }
    }
}
