package nl.thomasvanminnen.persistence.interfaces;

public interface UserDAO {
    Boolean userExists(String userName, String userPassword);
}
