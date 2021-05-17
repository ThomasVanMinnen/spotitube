package nl.thomasvanminnen.persistence;

import nl.thomasvanminnen.persistence.factories.interfaces.ConnectionFactory;
import nl.thomasvanminnen.persistence.interfaces.TokenDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Default
public class TokenDAOImpl implements TokenDAO {
    private final ConnectionFactory connectionFactory;

    @Inject
    public TokenDAOImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Boolean insertToken(String username, String token) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tokens(userid, token) VALUES ((SELECT u.id FROM users AS u WHERE u.username = ?), ?)");
        ) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, token);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            throw new PersistenceException(sqlException);
        }
        return true;
    }
}
