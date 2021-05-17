package nl.thomasvanminnen.persistence;

import nl.thomasvanminnen.persistence.factories.ConnectionFactoryImpl;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDAOImplTest{
    private UserDAOImpl sut;

    @BeforeEach
    void setUp() {
        this.sut = new UserDAOImpl(new ConnectionFactoryImpl());

    }

    private void loadDatabaseFixture(String filename) throws SQLException {
        RunScript.execute(new ConnectionFactoryImpl().getConnection(),
                new InputStreamReader(
                        this.getClass().getResourceAsStream("/fixtures/" + filename)));
    }

    @Test
    void returnTrueWhenUserExists() throws SQLException {
//        loadDatabaseFixture("fixtureBaseData.sql");
//
//        Boolean actualResult = sut.userExists("thomas", "thomas");
//
//        assertEquals(true, actualResult);
    }
}
