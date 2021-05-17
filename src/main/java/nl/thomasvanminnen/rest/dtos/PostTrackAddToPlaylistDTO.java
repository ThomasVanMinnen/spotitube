package nl.thomasvanminnen.rest.dtos;

public class PostTrackAddToPlaylistDTO {
    public int id;
    public Boolean offlineAvailable;

    public PostTrackAddToPlaylistDTO() {
    }

    public PostTrackAddToPlaylistDTO(int id, Boolean offlineAvailable) {
        this.id = id;
        this.offlineAvailable = offlineAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(Boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }
}
