package nl.thomasvanminnen.service;

import nl.thomasvanminnen.persistence.interfaces.UserDAO;
import nl.thomasvanminnen.rest.dtos.LoginDTO;
import nl.thomasvanminnen.rest.dtos.TokenDTO;
import nl.thomasvanminnen.rest.exceptions.IncorrectUserDetailsException;
import nl.thomasvanminnen.rest.exceptions.MissingParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthenticationServiceImplTest {
    private AuthenticationServiceImpl sut;
    private UserDAO userDAOMock;

    @BeforeEach
    void setUp() {
        this.userDAOMock = Mockito.mock(UserDAO.class);
        this.sut = new AuthenticationServiceImpl(userDAOMock);
    }

    @Test
    void returnTrueWhenUserExists() {
        when(userDAOMock.userExists("thomas", "thomas")).thenReturn(true);
        Boolean actualResult = sut.authenticateUserLogin("thomas", "thomas");
        verify(userDAOMock).userExists("thomas", "thomas");
        assertEquals(true, actualResult);
    }

    @Test
    void getExceptionMessageWhenUserDetailsAreMissing() {
        Throwable exception = assertThrows(MissingParameterException.class, () -> sut.authenticateUserLogin("", ""));
        assertEquals("Niet alle gegevens zijn ingevuld", exception.getMessage());
    }
}
