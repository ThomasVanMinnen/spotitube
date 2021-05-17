package nl.thomasvanminnen.service.interfaces;

import nl.thomasvanminnen.rest.dtos.LoginDTO;
import nl.thomasvanminnen.rest.dtos.TokenDTO;

public interface LoginService {
    TokenDTO loginUser(LoginDTO loginDTO);
}
