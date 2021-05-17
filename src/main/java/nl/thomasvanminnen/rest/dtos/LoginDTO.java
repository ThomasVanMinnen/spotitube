package nl.thomasvanminnen.rest.dtos;

public class LoginDTO {
    private String password;
    private String user;

    public LoginDTO() {
    }

    public LoginDTO(String password, String user) {
        this.password = password;
        this.user = user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
