package model;

import java.time.LocalDate;

public class Contract {
    private int id;
    private int pointId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int floor;
    private double area;
    private boolean airConditioner;
    private double rent;

    public Contract(int id, int pointId, LocalDate startDate, LocalDate endDate,
                    int floor, double area, boolean airConditioner, double rent) {
        this.id = id;
        this.pointId = pointId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.floor = floor;
        this.area = area;
        this.airConditioner = airConditioner;
        this.rent = rent;
    }

    public int getId() { return id; }
    public int getPointId() { return pointId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public int getFloor() { return floor; }
    public double getArea() { return area; }
    public boolean isAirConditioner() { return airConditioner; }
    public double getRent() { return rent; }
}

