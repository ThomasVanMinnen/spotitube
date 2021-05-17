package nl.thomasvanminnen.service;

import nl.thomasvanminnen.rest.dtos.LoginDTO;
import nl.thomasvanminnen.rest.dtos.TokenDTO;
import nl.thomasvanminnen.service.interfaces.AuthenticationService;
import nl.thomasvanminnen.service.interfaces.LoginService;
import nl.thomasvanminnen.service.interfaces.TokenService;

import javax.inject.Inject;

public class LoginServiceImpl implements LoginService {
    private AuthenticationService authenticationService;
    private TokenService tokenService;
    private CreateTokenDTOImpl createTokenDTO;

    public LoginServiceImpl() {
    }

    @Inject
    public LoginServiceImpl(AuthenticationService authenticationService, TokenService tokenService, CreateTokenDTOImpl createTokenDTO) {
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
        this.createTokenDTO = createTokenDTO;
    }

    @Override
    public TokenDTO loginUser(LoginDTO loginDTO){
        TokenDTO tokenDTO = null;

        if (authenticationService.authenticateUserLogin(loginDTO.getUser(), loginDTO.getPassword())) {
            tokenDTO = createTokenDTO.newTokenDTO(loginDTO.getUser());
            tokenService.insertUserToken(tokenDTO);
            return tokenDTO;
        } else {
            return tokenDTO;
        }
    }
}
