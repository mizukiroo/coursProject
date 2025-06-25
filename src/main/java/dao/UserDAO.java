package dao;

import model.User;
import java.sql.*;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    //Регистрация без client_id
    public boolean registerUser(String login, String passwordHash, String role) {
        String sql = "INSERT INTO users (login, password_u, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, passwordHash);
            stmt.setString(3, role);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Ошибка регистрации: " + e.getMessage());
            return false;
        }
    }

    //Регистрация с привязкой к client_id
    public boolean insertUser(String login, String passwordHash, String role, int clientId) {
        String sql = "INSERT INTO users (login, password_u, role, client_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, passwordHash);
            stmt.setString(3, role);
            stmt.setInt(4, clientId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Ошибка добавления пользователя: " + e.getMessage());
            return false;
        }
    }

    //Проверка занятости логина
    public boolean existsByLogin(String login) {
        String sql = "SELECT 1 FROM users WHERE login = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Ошибка проверки логина: " + e.getMessage());
            return false;
        }
    }

    //Аутентификация
    public User authenticate(String login, String passwordHash) {
        String sql = "SELECT * FROM users WHERE login = ? AND password_u = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, passwordHash);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("login"),
                        rs.getString("password_u"),
                        rs.getString("role"),
                        rs.getInt("client_id")
                );
            }
        } catch (SQLException e) {
            System.out.println("Ошибка входа: " + e.getMessage());
        }
        return null;
    }

    //Получение client_id по логину
    public int getClientIdByUsername(String login) {
        String sql = "SELECT client_id FROM users WHERE login = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("client_id");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения client_id: " + e.getMessage());
        }
        return -1;
    }
}
