package nl.thomasvanminnen.service.interfaces;

import nl.thomasvanminnen.rest.dtos.TokenDTO;

public interface TokenService {
    Boolean insertUserToken(TokenDTO tokenDTO);
}
