package nl.thomasvanminnen.persistence.factories.interfaces;

import javax.persistence.PersistenceException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Properties;

public interface ConnectionFactory {
    String DB_PROPERTIES_FILENAME = ConnectionFactory.getPropertiesLocation().toString();

    static File getPropertiesLocation() {
        URL url = ConnectionFactory.class.getClassLoader().getResource("config.properties");
        File file = null;
        try {
            file = new File(Paths.get(url.toURI()).toFile().getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return file;
    }

    Connection getConnection();

    default Properties readProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(DB_PROPERTIES_FILENAME));
        } catch (IOException e) {
            throw new PersistenceException(e);
        }

        return properties;
    }
}
