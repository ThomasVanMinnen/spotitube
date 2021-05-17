package nl.thomasvanminnen.persistence;

import nl.thomasvanminnen.persistence.factories.interfaces.ConnectionFactory;
import nl.thomasvanminnen.persistence.interfaces.UserDAO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class UserDAOImpl implements UserDAO {
    private final ConnectionFactory connectionFactory;

    @Inject
    public UserDAOImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Boolean userExists(String userName, String userPassword) {
        Boolean authenticated = false;
        try (
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = '" + userName + "' AND password = '" + userPassword + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            if (resultSet.next()) {
                authenticated = true;
            }
        } catch (SQLException sqlException) {
            throw new PersistenceException(sqlException);
        }
        return authenticated;
    }
}
