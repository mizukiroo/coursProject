package dao;

import model.Client;
import java.sql.*;

public class ClientDAO {
    private final Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    public int insertClient(String name, String address, String phone, String requisites, String contactPerson) {
        String sql = "INSERT INTO clients (name, address, phone, requisites, contact_person) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, phone);
            stmt.setString(4, requisites);
            stmt.setString(5, contactPerson);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Не удалось создать клиента");

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) return generatedKeys.getInt(1);
                else throw new SQLException("Не удалось получить client_id.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении клиента: " + e.getMessage());
            return -1;
        }
    }

    //Получить клиента по логину
    public Client findByUsername(String username) {
        String sql = """
            SELECT c.client_id, c.name, c.address, c.phone, c.requisites, c.contact_person
            FROM clients c
            JOIN users u ON u.client_id = c.client_id
            WHERE u.login = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Client(
                        rs.getInt("client_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("requisites"),
                        rs.getString("contact_person")
                );
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении клиента по логину: " + e.getMessage());
        }
        return null;
    }

    //Обновить клиента по ID
    public boolean updateClient(int clientId, String name, String address, String phone, String requisites, String contactPerson) {
        String sql = """
            UPDATE clients SET name = ?, address = ?, phone = ?, requisites = ?, contact_person = ?
            WHERE client_id = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, phone);
            stmt.setString(4, requisites);
            stmt.setString(5, contactPerson);
            stmt.setInt(6, clientId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Ошибка при обновлении данных клиента: " + e.getMessage());
            return false;
        }
    }
}

