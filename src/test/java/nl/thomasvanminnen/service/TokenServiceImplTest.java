package nl.thomasvanminnen.service;

import nl.thomasvanminnen.persistence.interfaces.TokenDAO;
import nl.thomasvanminnen.rest.dtos.TokenDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TokenServiceImplTest {
    private TokenServiceImpl sut;
    private TokenDAO tokenDAOMock;

    @BeforeEach
    void setUp() {
        this.tokenDAOMock = Mockito.mock(TokenDAO.class);
        this.sut = new TokenServiceImpl(tokenDAOMock);
    }

    @Test
    void returnTrueWhenNewUserTokenIsInserted() {
        TokenDTO tokenDTO = new TokenDTO("", "thomas");
        when(tokenDAOMock.insertToken(tokenDTO.getUser(), tokenDTO.getToken())).thenReturn(true);
        Boolean actualResult = sut.insertUserToken(tokenDTO);
        assertEquals(true, actualResult);
    }
}
