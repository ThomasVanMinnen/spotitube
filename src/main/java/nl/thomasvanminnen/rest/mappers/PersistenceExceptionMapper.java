package nl.thomasvanminnen.rest.mappers;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {
    @Override
    public Response toResponse(PersistenceException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
