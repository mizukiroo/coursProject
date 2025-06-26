package model;

import java.time.LocalDate;

public class Contract {
    private int id;
    private int clientId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Contract(int id, int clientId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getClientId() { return clientId; }

    public void setClientId(int clientId) { this.clientId = clientId; }

    public LocalDate getStartDate() { return startDate; }

    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}

