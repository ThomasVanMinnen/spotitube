package nl.thomasvanminnen.persistence;

import nl.thomasvanminnen.persistence.factories.interfaces.ConnectionFactory;
import nl.thomasvanminnen.persistence.interfaces.TrackDAO;
import nl.thomasvanminnen.rest.dtos.TrackDTO;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackDAOImpl implements TrackDAO {
    private final ConnectionFactory connectionFactory;

    @Inject
    public TrackDAOImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public List<TrackDTO> getAllTracksFromPlaylist(int playlistId) {
        List<TrackDTO> resultList = new ArrayList<>();

        try (
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT t.*, ktpt.offlineAvailable FROM track AS t LEFT JOIN kt_playlist_track AS ktpt ON ktpt.trackid = t.id WHERE ktpt.playlistid = " + playlistId);
            ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                TrackDTO track = new TrackDTO(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("performer"), resultSet.getInt("duration"), resultSet.getString("album"), resultSet.getInt("playcount"), resultSet.getString("publicationDate"), resultSet.getString("description"), resultSet.getBoolean("offlineAvailable"));
                resultList.add(track);
            }
        } catch (SQLException sqlException) {
            throw new PersistenceException(sqlException);
        }

        return resultList;
    }

    @Override
    public List<TrackDTO> getAllTracksThatAreNotInPlaylist(int playlistId) {
        List<TrackDTO> resultList = new ArrayList<>();

        try (
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT t.*, (SELECT ktpt.offlineAvailable FROM kt_playlist_track AS ktpt WHERE ktpt.trackid = t.id AND ktpt.playlistid = " + playlistId + ") AS offlineAvailable FROM track AS t WHERE (SELECT COUNT(*) FROM kt_playlist_track AS ktpt WHERE ktpt.playlistid = " + playlistId + " AND ktpt.trackid = t.id) < 1");
            ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                TrackDTO track = new TrackDTO(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("performer"), resultSet.getInt("duration"), resultSet.getString("album"), resultSet.getInt("playcount"), resultSet.getString("publicationdate"), resultSet.getString("description"), resultSet.getBoolean("offlineavailable"));
                resultList.add(track);
            }
        } catch (SQLException sqlException) {
            throw new PersistenceException(sqlException);
        }
        return resultList;
    }

    @Override
    public Boolean removeTrackFromPlaylistWithId(int trackId, int playlistId) {
        try (
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM kt_playlist_track WHERE trackid = ? AND playlistid = ?");
        ) {
            preparedStatement.setInt(1, trackId);
            preparedStatement.setInt(2, playlistId);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            throw new PersistenceException(sqlException);
        }
        return true;
    }

    @Override
    public Boolean insertNewTrack(int playlistId, int trackId, Boolean offlineAvailable) {
        try (
            Connection connection = connectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO kt_playlist_track(playlistid, trackid, offlineAvailable) VALUES (?, ?, ?)");
        ) {
            preparedStatement.setInt(1, playlistId);
            preparedStatement.setInt(2, trackId);
            preparedStatement.setBoolean(3, offlineAvailable);
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            throw new PersistenceException(sqlException);
        }
        return true;
    }
}
