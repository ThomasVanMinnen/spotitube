package nl.thomasvanminnen.rest.mappers;

import nl.thomasvanminnen.rest.exceptions.IncorrectUserDetailsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncorrectUserDetailsExceptionMapperTest {
    private IncorrectUserDetailsExceptionMapper sut;

    @BeforeEach
    void setUp() {
        this.sut = new IncorrectUserDetailsExceptionMapper();
    }

    @Test
    void return401WhenUserDoesntExsist() {
        Response actualResponse = sut.toResponse(new IncorrectUserDetailsException(""));
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), actualResponse.getStatus());
    }
}
