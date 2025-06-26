package dao;

import java.sql.*;
import java.time.LocalDate;
import model.Database;

public class ContractDAO {
    private final Connection connection;

    public ContractDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createContractWithPoint(String username, int pointId, LocalDate startDate, LocalDate endDate) {
        try {
            connection.setAutoCommit(false);

            // 1. Получаем client_id
            String clientSql = """
                    SELECT c.id, c.start_date, c.end_date, tp.floor, tp.area, tp.daily_rent
                    FROM contracts c
                    JOIN contract_trade_point ct ON c.id = ct.contract_id
                    JOIN trade_points tp ON ct.point_id = tp.id
                    WHERE c.client_id = ?
                    """;
            int clientId;
            try (PreparedStatement stmt = connection.prepareStatement(clientSql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) return false;
                clientId = rs.getInt("client_id");
            }

            // 2. Вставляем в contracts
            int contractId;
            String insertContract = "INSERT INTO contracts (client_id, start_date, end_date) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertContract, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, clientId);
                stmt.setDate(2, Date.valueOf(startDate));
                stmt.setDate(3, Date.valueOf(endDate));
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (!rs.next()) throw new SQLException("Не удалось получить ID договора");
                contractId = rs.getInt(1);
            }

            // 3. Вставляем в contract_trade_point
            String insertLink = "INSERT INTO contract_trade_point (contract_id, point_id) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(insertLink)) {
                stmt.setInt(1, contractId);
                stmt.setInt(2, pointId);
                stmt.executeUpdate();
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Ошибка отката транзакции: " + ex.getMessage());
            }
            System.out.println("Ошибка создания договора: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignored) {}
        }
    }
}
