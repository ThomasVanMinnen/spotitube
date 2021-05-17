package nl.thomasvanminnen.persistence.interfaces;

public interface TokenDAO {
    Boolean insertToken(String username, String token);
}
