package nl.thomasvanminnen.service;

import nl.thomasvanminnen.rest.dtos.TokenDTO;
import nl.thomasvanminnen.service.interfaces.TokenGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CreateTokenDTOImplTest {
    private CreateTokenDTOImpl sut = new CreateTokenDTOImpl();
    private TokenGenerator tokenGeneratorMock;
    private TokenDTO tokenDTOMock = new TokenDTO();

    @BeforeEach
    void setUp() {
        this.tokenGeneratorMock = Mockito.mock(TokenGenerator.class);
        this.sut = new CreateTokenDTOImpl(tokenGeneratorMock);
    }

    @Test
    void returnsObjectFromClassTokenDTO() {
        when(tokenGeneratorMock.GenerateToken()).thenReturn("");

        TokenDTO actualResult = sut.newTokenDTO("thomas");
        TokenDTO expected = new TokenDTO();

        assertEquals(expected.getClass(), actualResult.getClass());
    }
}
