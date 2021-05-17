package nl.thomasvanminnen.service.interfaces;

public interface AuthenticationService {
    boolean authenticateUserLogin(String user, String password);
}
