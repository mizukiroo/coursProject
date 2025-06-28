package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TradePoint {
    private int id;
    private int floor;
    private double area;
    private boolean airConditioner;
    private double dailyRent;
    private int contractId;

    public TradePoint(int id, int floor, double area, boolean airConditioner, double dailyRent, int contractId) {
        this.id = id;
        this.floor = floor;
        this.area = area;
        this.airConditioner = airConditioner;
        this.dailyRent = dailyRent;
        this.contractId = contractId;
    }

    public TradePoint(int id, int floor, double area, boolean airConditioner, double dailyRent) {
        this(id, floor, area, airConditioner, dailyRent, -1); // -1 означает "нет договора"
    }

    public int getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public double getArea() {
        return area;
    }

    public boolean hasAirConditioner() {
        return airConditioner;
    }

    public double getDailyRent() {
        return dailyRent;
    }

    public int getContractId() {
        return contractId;
    }

    @Override
    public String toString() {
        return String.format("Этаж %d | %.1f м² | %s | %.2f руб/день",
                floor,
                area,
                airConditioner ? "с кондиц." : "без кондиц.",
                dailyRent
        );
    }

    public static List<TradePoint> loadAll(Connection conn) {
        List<TradePoint> list = new ArrayList<>();
        String sql = "SELECT * FROM trade_points";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new TradePoint(
                        rs.getInt("id"),
                        rs.getInt("floor"),
                        rs.getDouble("area"),
                        rs.getBoolean("air_conditioner"),
                        rs.getDouble("daily_rent")
                        // contractId не нужен — это просто справочник
                ));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка загрузки точек: " + e.getMessage());
        }
        return list;
    }
}
