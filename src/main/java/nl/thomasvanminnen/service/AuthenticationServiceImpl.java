package nl.thomasvanminnen.service;

import nl.thomasvanminnen.persistence.interfaces.UserDAO;
import nl.thomasvanminnen.rest.exceptions.MissingParameterException;
import nl.thomasvanminnen.service.interfaces.AuthenticationService;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserDAO userDAO;

    @Inject
    public AuthenticationServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public boolean authenticateUserLogin(String user, String password) {
        if (!user.isEmpty() || !password.isEmpty()){
            return userDAO.userExists(user, password);
        }else {
            throw new MissingParameterException("Niet alle gegevens zijn ingevuld");
        }
    }
}
