package model;

import java.time.LocalDate;

public class ContractInfo {
    private final String username;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int floor;
    private final double area;
    private final double dailyRent;

    public ContractInfo(String username, LocalDate startDate, LocalDate endDate, int floor, double area, double dailyRent) {
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
        this.floor = floor;
        this.area = area;
        this.dailyRent = dailyRent;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getFloor() {
        return floor;
    }

    public double getArea() {
        return area;
    }

    public double getDailyRent() {
        return dailyRent;
    }
}
