package nl.thomasvanminnen.service;

import nl.thomasvanminnen.rest.dtos.TokenDTO;
import nl.thomasvanminnen.service.interfaces.CreateTokenDTO;
import nl.thomasvanminnen.service.interfaces.TokenGenerator;

import javax.inject.Inject;

public class CreateTokenDTOImpl implements CreateTokenDTO {
    private TokenGenerator tokenGenerator;

    public CreateTokenDTOImpl() {
    }

    @Inject
    public CreateTokenDTOImpl(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public TokenDTO newTokenDTO(String username) {
        return new TokenDTO(tokenGenerator.GenerateToken(), username);
    }
}
