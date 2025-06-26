package dao;

import model.TradePoint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TradePointDAO {
    private final Connection connection;

    public TradePointDAO(Connection connection) {
        this.connection = connection;
    }

    public List<TradePoint> getAllPoints() {
        List<TradePoint> points = new ArrayList<>();
        String sql = "SELECT * FROM trade_points";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TradePoint point = new TradePoint(
                        rs.getInt("id"),
                        rs.getInt("floor"),
                        rs.getDouble("area"),
                        rs.getBoolean("air_conditioner"),
                        rs.getDouble("daily_rent")
                );
                points.add(point);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка загрузки торговых точек: " + e.getMessage());
        }

        return points;
    }

    public TradePoint getById(int id) {
        String sql = "SELECT * FROM trade_points WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new TradePoint(
                        rs.getInt("id"),
                        rs.getInt("floor"),
                        rs.getDouble("area"),
                        rs.getBoolean("air_conditioner"),
                        rs.getDouble("daily_rent")
                );
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении точки по ID: " + e.getMessage());
        }

        return null;
    }

    public boolean addTradePoint(int floor, double area, boolean airConditioner, double dailyRent) {
        String sql = "INSERT INTO trade_points (floor, area, air_conditioner, daily_rent) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, floor);
            stmt.setDouble(2, area);
            stmt.setBoolean(3, airConditioner);
            stmt.setDouble(4, dailyRent);

            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении точки: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM trade_points WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении точки: " + e.getMessage());
            return false;
        }
    }
}
