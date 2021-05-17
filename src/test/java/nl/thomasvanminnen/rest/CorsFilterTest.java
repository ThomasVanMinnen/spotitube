package nl.thomasvanminnen.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedHashMap;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CorsFilterTest {

    private CorsFilter sut;

    @BeforeEach
    void setUp() {
        sut = new CorsFilter();
    }

    @Test
    void corsHeadersAreAddedToResponse() throws IOException {
        ContainerRequestContext requestContextMock = Mockito.mock(ContainerRequestContext.class);
        ContainerResponseContext responseContextMock = Mockito.mock(ContainerResponseContext.class);

        MultivaluedHashMap headerMap = new MultivaluedHashMap();
        when(responseContextMock.getHeaders()).thenReturn(headerMap);

        sut.filter(requestContextMock, responseContextMock);

        assertTrue(headerMap.get("Access-Control-Allow-Origin").contains("https://jenkins.aimsites.nl"));
        assertTrue(headerMap.get("Access-Control-Allow-Credentials").contains("true"));
        assertTrue(headerMap.get("Access-Control-Allow-Headers").contains("origin, content-type, accept, authorization"));
        assertTrue(headerMap.get("Access-Control-Allow-Methods").contains("GET, POST, PUT, DELETE, OPTIONS, HEAD"));
    }
}
