package nl.thomasvanminnen.service;

import nl.thomasvanminnen.rest.dtos.LoginDTO;
import nl.thomasvanminnen.rest.dtos.TokenDTO;
import nl.thomasvanminnen.service.interfaces.AuthenticationService;
import nl.thomasvanminnen.service.interfaces.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LoginServiceImplTest {
    private LoginServiceImpl sut = new LoginServiceImpl();
    private AuthenticationService authenticationServiceMock;
    private TokenService tokenServiceMock;
    private CreateTokenDTOImpl createTokenDTO;

    @BeforeEach
    void setUp() {
        this.authenticationServiceMock = Mockito.mock(AuthenticationService.class);
        this.tokenServiceMock = Mockito.mock(TokenService.class);
        this.createTokenDTO = Mockito.mock(CreateTokenDTOImpl.class);
        this.sut = new LoginServiceImpl(authenticationServiceMock, tokenServiceMock, createTokenDTO);
    }

    @Test
    void returnTokenDTOWhenAuthenticateUserLoginSuccesfull() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUser("thomas");
        loginDTO.setPassword("thomas");
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setUser(loginDTO.getUser());
        tokenDTO.setToken("4fa8eb3e-5c4a-4171-baf0-32b4dedb8a20");

        when(authenticationServiceMock.authenticateUserLogin(loginDTO.getUser(), loginDTO.getPassword())).thenReturn(true);
        when(createTokenDTO.newTokenDTO(loginDTO.getUser())).thenReturn(tokenDTO);

        TokenDTO actualResult = sut.loginUser(loginDTO);

        assertEquals(tokenDTO, actualResult);
    }

    @Test
    void returnNullTokenDTOWhenAuthenticateUserLoginSuccesfull() {
        LoginDTO loginDTO = new LoginDTO("thomas", "thomas");
        TokenDTO tokenDTO = null;

        when(authenticationServiceMock.authenticateUserLogin(loginDTO.getUser(), loginDTO.getPassword())).thenReturn(false);
        TokenDTO actualResult = sut.loginUser(loginDTO);

        assertEquals(tokenDTO, actualResult);
    }
}
