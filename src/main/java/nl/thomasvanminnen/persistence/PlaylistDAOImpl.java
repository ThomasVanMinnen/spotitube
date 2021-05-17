package nl.thomasvanminnen.persistence;

import nl.thomasvanminnen.persistence.factories.interfaces.ConnectionFactory;
import nl.thomasvanminnen.persistence.interfaces.PlaylistDAO;
import nl.thomasvanminnen.rest.dtos.PlaylistDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Default
public class PlaylistDAOImpl implements PlaylistDAO {
    private final ConnectionFactory connectionFactory;
    private final static Logger LOGGER = Logger.getLogger("'PlaylistDAO");
    

    @Inject
    public PlaylistDAOImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<PlaylistDTO> getAllPlaylists(String token) {
        List<PlaylistDTO> resultList = new ArrayList<>();

        try (
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT playlists.id, playlists.name, IF((SELECT t.userid FROM tokens AS t WHERE t.token = '"+token+"') = playlists.owner, true, false) AS owner FROM playlists");
            ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                PlaylistDTO playlist = new PlaylistDTO(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getBoolean("owner"));
                resultList.add(playlist);
            }
        } catch (SQLException sqlException) {
            LOGGER.severe("SQLException ingetAllPlaylists()");
            throw new PersistenceException(sqlException);
        }
        return resultList;
    }

    @Override
    public Boolean insertNewPlaylist(PlaylistDTO playlist, String token) {
        try (
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO playlists(name, owner) VALUES (?, (SELECT t.userid FROM tokens AS t WHERE t.token = ?))");
        ) {
            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setString(2, token);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            throw new PersistenceException(sqlException);
        }
        return true;
    }

    @Override
    public Boolean updatePlaylistWithId(PlaylistDTO playlist, String token, int id) {
        try (
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE playlists SET name = ? WHERE id = ? AND owner = (SELECT t.userid FROM tokens AS t WHERE t.token = ?)");
        ) {
            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setInt(2, id);
            preparedStatement.setString(3, token);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            throw new PersistenceException(sqlException);
        }
        return true;
    }

    @Override
    public Boolean deletePlaylistWithId(int id, String token) {
        try (
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM playlists WHERE playlists.id = ? AND playlists.owner = (SELECT t.userid FROM tokens AS t WHERE t.token = ?)");
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, token);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            //TODO: add loggers at every catch
            throw new PersistenceException(sqlException);
        }
        return true;
    }

    @Override
    public int totalPlaylistsLength() {
        int totalLength = 0;

        try (
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(t.duration) AS length FROM playlists AS p LEFT JOIN kt_playlist_track AS ktpt ON ktpt.playlistid = p.id LEFT JOIN track AS t ON ktpt.trackid = t.id");
            ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                totalLength = resultSet.getInt("length");
            }
        } catch (SQLException sqlException) {
            throw new PersistenceException(sqlException);
        }
        return totalLength;
    }
}
