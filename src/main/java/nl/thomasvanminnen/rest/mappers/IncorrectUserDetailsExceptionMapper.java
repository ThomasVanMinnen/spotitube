package nl.thomasvanminnen.rest.mappers;

import nl.thomasvanminnen.rest.dtos.RestErrorDTO;
import nl.thomasvanminnen.rest.exceptions.IncorrectUserDetailsException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IncorrectUserDetailsExceptionMapper implements ExceptionMapper<IncorrectUserDetailsException> {

    @Override
    public Response toResponse(IncorrectUserDetailsException e) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(new RestErrorDTO(1, e.getMessage())).build();
    }
}
