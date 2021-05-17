package nl.thomasvanminnen.rest.mappers;

import nl.thomasvanminnen.rest.dtos.RestErrorDTO;
import nl.thomasvanminnen.rest.exceptions.MissingParameterException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MissingParameterExceptionMapper implements ExceptionMapper<MissingParameterException> {

    @Override
    public Response toResponse(MissingParameterException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new RestErrorDTO(2, e.getMessage())).build();
    }
}
