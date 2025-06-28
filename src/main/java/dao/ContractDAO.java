package dao;

import model.Contract;
import model.Database;
import model.TradePoint;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO {
    private final Connection connection;

    public ContractDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createContractWithPoint(String username, int pointId, LocalDate startDate, LocalDate endDate) {
        try {
            connection.setAutoCommit(false);

            // Получаем client_id по username
            int clientId;
            String clientSql = """
                SELECT c.client_id
                FROM users u
                JOIN clients c ON u.client_id = c.client_id
                WHERE u.login = ?
            """;
            try (PreparedStatement stmt = connection.prepareStatement(clientSql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) return false;
                clientId = rs.getInt("client_id");
            }

            // Вставляем договор
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

            // Привязываем точку к договору
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

    public List<Contract> getContractsByUsername(String username) {
        List<Contract> contracts = new ArrayList<>();

        String sql = """
        SELECT ct.id AS contract_id, ct.start_date, ct.end_date,
               tp.id AS point_id, tp.floor, tp.area, tp.air_conditioner, tp.daily_rent
        FROM users u
        JOIN clients c ON u.client_id = c.client_id
        JOIN contracts ct ON c.client_id = ct.client_id
        JOIN contract_trade_point ctp ON ct.id = ctp.contract_id
        JOIN trade_points tp ON ctp.point_id = tp.id
        WHERE u.login = ?
    """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Contract contract = new Contract(
                        rs.getInt("contract_id"),
                        rs.getInt("point_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getInt("floor"),
                        rs.getDouble("area"),
                        rs.getBoolean("air_conditioner"),
                        rs.getDouble("daily_rent")
                );
                contracts.add(contract);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения договоров пользователя: " + e.getMessage());
        }

        return contracts;
    }

}

