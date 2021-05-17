package nl.thomasvanminnen.service.interfaces;

import nl.thomasvanminnen.rest.dtos.TokenDTO;

public interface CreateTokenDTO {
    TokenDTO newTokenDTO(String username);
}
