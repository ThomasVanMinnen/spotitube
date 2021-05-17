package nl.thomasvanminnen.service;

import nl.thomasvanminnen.persistence.interfaces.TokenDAO;
import nl.thomasvanminnen.rest.dtos.TokenDTO;
import nl.thomasvanminnen.service.interfaces.TokenService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class TokenServiceImpl implements TokenService {
    private final TokenDAO tokenDAO;

    @Inject
    public TokenServiceImpl(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    @Override
    public Boolean insertUserToken(TokenDTO tokenDTO) {
        return tokenDAO.insertToken(tokenDTO.getUser(), tokenDTO.getToken());
    }
}
