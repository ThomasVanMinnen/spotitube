package nl.thomasvanminnen.rest.resources;

import nl.thomasvanminnen.rest.dtos.LoginDTO;
import nl.thomasvanminnen.rest.dtos.TokenDTO;
import nl.thomasvanminnen.rest.exceptions.IncorrectUserDetailsException;
import nl.thomasvanminnen.service.interfaces.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginResourceTest {
    private LoginResource sut = new LoginResource();
    private LoginService loginServiceMock;

    @BeforeEach
    void setUp() {
        loginServiceMock = Mockito.mock(LoginService.class);
        sut = new LoginResource(loginServiceMock);
    }

    @Test
    void get201WhenLogInSuccessfull() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser("thomas");
        loginDTO.setPassword("thomas");

        when(loginServiceMock.loginUser(loginDTO))
                .thenReturn(new TokenDTO("b89c6b7d-ec12-42ee-b928-8427c355b3b7", "thomas"));

        Response actualResponse = sut.login(loginDTO);
        verify(loginServiceMock).loginUser(loginDTO);
        assertEquals(Response.Status.CREATED.getStatusCode(), actualResponse.getStatus());
    }

    @Test
    void getExceptionMessageWhenUserIsNotFound() {
        LoginDTO loginDTO = new LoginDTO("thomas", "thomas");
        TokenDTO tokenDTO = null;

        when(loginServiceMock.loginUser(loginDTO)).thenReturn(tokenDTO);

        Throwable exception = assertThrows(IncorrectUserDetailsException.class, () -> sut.login(loginDTO));
        assertEquals("Gebruiker is niet gevonden", exception.getMessage());
    }
}
