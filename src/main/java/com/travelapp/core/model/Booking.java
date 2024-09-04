package com.travelapp.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Booking {
    @Id
    private String id;
    private User user;
    private String type; // e.g., FLIGHT, HOTEL, CAR_RENTAL
    private String referenceNumber;
    private LocalDate bookingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private double amount;
    private String userId;

    public Booking(String id, User user, String type, String referenceNumber, LocalDate bookingDate, LocalDate startDate, LocalDate endDate, double amount) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.referenceNumber = referenceNumber;
        this.bookingDate = bookingDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public Booking() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
