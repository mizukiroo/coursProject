package model;

public class User {
    private int userId;
    private String login;
    private String passwordHash;
    private String role;

    public User(int userId, String login, String passwordHash, String role, int clientId) {
        this.userId = userId;
        this.login = login;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }
}

