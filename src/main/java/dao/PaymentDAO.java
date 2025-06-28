package dao;

import model.Payment;
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
                   c.id AS contract_id
            FROM contracts c
            JOIN contract_trade_point ctp ON c.id = ctp.contract_id
            JOIN trade_points tp ON ctp.point_id = tp.id
            WHERE c.client_id = ? AND c.end_date >= CURRENT_DATE
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

    public List<Payment> getAllPaymentsWithDetails() {
        List<Payment> payments = new ArrayList<>();

        String sql = """
            SELECT p.contract_id, p.point_id, p.payment_date, p.amount,
                   tp.floor, tp.area, tp.daily_rent
            FROM payments p
            JOIN trade_points tp ON p.point_id = tp.id
            ORDER BY p.payment_date DESC
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("contract_id"),
                        rs.getInt("point_id"),
                        rs.getDate("payment_date").toLocalDate(),
                        rs.getDouble("amount"),
                        rs.getInt("floor"),
                        rs.getDouble("area"),
                        rs.getDouble("daily_rent")
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при загрузке платежей: " + e.getMessage());
        }

        return payments;
    }
}
