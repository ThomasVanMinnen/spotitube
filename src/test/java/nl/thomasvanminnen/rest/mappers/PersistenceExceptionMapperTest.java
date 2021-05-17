package nl.thomasvanminnen.rest.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersistenceExceptionMapperTest {
    private PersistenceExceptionMapper sut;

    @BeforeEach
    void setUp() {
        this.sut = new PersistenceExceptionMapper();
    }

    @Test
    void return401WhenUserDoesntExsist() {
        Response actualResponse = sut.toResponse(new PersistenceException(""));
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), actualResponse.getStatus());
    }
}
