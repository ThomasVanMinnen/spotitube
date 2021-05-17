package nl.thomasvanminnen.persistence.factories;

import nl.thomasvanminnen.persistence.factories.interfaces.ConnectionFactory;

import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImpl implements ConnectionFactory {

    private Properties properties = readProperties();

    public ConnectionFactoryImpl() {
        try {
            Class.forName(properties.getProperty("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public Connection getConnection() {
        var url = properties.getProperty("db.url");
        var user = properties.getProperty("db.username");
        var password = properties.getProperty("db.password");

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlException) {
            throw new PersistenceException(sqlException);
        }
    }

}
