package nl.thomasvanminnen.rest.mappers;

import nl.thomasvanminnen.rest.exceptions.MissingParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MissingParameterExceptionMapperTest {
    private MissingParameterExceptionMapper sut;

    @BeforeEach
    void setUp() {
        this.sut = new MissingParameterExceptionMapper();
    }

    @Test
    void return401WhenUserDoesntExsist() {
        Response actualResponse = sut.toResponse(new MissingParameterException(""));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), actualResponse.getStatus());
    }
}
