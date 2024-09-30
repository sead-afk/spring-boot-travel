package com.travelapp.core.model;

import java.time.LocalDate;

public class Trip {
    private String id;
    private User user;
    private Destination destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status; // e.g., PLANNED, COMPLETED, CANCELLED

    public Trip(String id, User user, Destination destination, LocalDate startDate, LocalDate endDate, String status) {
        this.id = id;
        this.user = user;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
