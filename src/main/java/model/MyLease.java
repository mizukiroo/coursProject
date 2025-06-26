package model;

import java.time.LocalDate;

public class MyLease {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int floor;
    private final double area;
    private final double totalCost;

    public MyLease(LocalDate startDate, LocalDate endDate, int floor, double area, double totalCost) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.floor = floor;
        this.area = area;
        this.totalCost = totalCost;
    }

    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public int getFloor() { return floor; }
    public double getArea() { return area; }
    public double getTotalCost() { return totalCost; }
}
