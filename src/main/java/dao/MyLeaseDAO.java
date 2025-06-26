package dao;

import model.MyLease;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyLeaseDAO {
    private final Connection connection;

    public MyLeaseDAO(Connection connection) {
        this.connection = connection;
    }

    public List<MyLease> getLeasesByUsername(String username) {
        String sql = """
            SELECT c.start_date, c.end_date, tp.floor, tp.area, tp.daily_rent
            FROM users u
            JOIN contracts c ON u.client_id = c.client_id
            JOIN contract_trade_point ctp ON c.id = ctp.contract_id
            JOIN trade_points tp ON ctp.point_id = tp.id
            WHERE u.login = ?
        """;

        List<MyLease> leases = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate start = rs.getDate("start_date").toLocalDate();
                LocalDate end = rs.getDate("end_date").toLocalDate();
                int floor = rs.getInt("floor");
                double area = rs.getDouble("area");
                double dailyRent = rs.getDouble("daily_rent");

                long days = end.toEpochDay() - start.toEpochDay() + 1;
                double totalCost = dailyRent * days;

                leases.add(new MyLease(start, end, floor, area, totalCost));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения аренд: " + e.getMessage());
        }

        return leases;
    }
}
