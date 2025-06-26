package dao;

import model.TradePoint;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private final Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertPayment(int contractId, int pointId, LocalDate paymentDate, double amount) {
        String sql = "INSERT INTO payments (contract_id, point_id, payment_date, amount) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, contractId);
            stmt.setInt(2, pointId);
            stmt.setDate(3, Date.valueOf(paymentDate));
            stmt.setDouble(4, amount);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении платежа: " + e.getMessage());
            return false;
        }
    }

    public List<TradePoint> getTradePointsByClient(int clientId) {
        List<TradePoint> points = new ArrayList<>();

        String sql = """
            SELECT tp.id, tp.floor, tp.area, tp.air_conditioner, tp.daily_rent, 
                   c.id AS contract_id  // Важно!
            FROM contracts c
            JOIN contract_trade_point ctp ON c.id = ctp.contract_id
            JOIN trade_points tp ON ctp.point_id = tp.id
            WHERE c.client_id = ? AND c.end_date >= CURRENT_DATE  // Только активные контракты
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TradePoint point = new TradePoint(
                        rs.getInt("id"),
                        rs.getInt("floor"),
                        rs.getDouble("area"),
                        rs.getBoolean("air_conditioner"),
                        rs.getDouble("daily_rent"),
                        rs.getInt("contract_id")
                );
                points.add(point);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при загрузке торговых точек клиента: " + e.getMessage());
        }

        return points;
    }
}
