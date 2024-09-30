package com.travelapp.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class    Booking {
    @Id
    private String id;
    //private User user;
    private Destination destination;
    private Hotel hotel;
    private Flight flight;
    //private Payment payment;
    //private Trip trip;
    private String type; // e.g., FLIGHT, HOTEL
    private String referenceNumber;
    private LocalDate bookingDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private double amount;



    public Booking() {

    }

    public Booking(LocalDate startDate, /*User user,*/ Destination destination, Hotel hotel, Flight flight, /*Payment payment, Trip trip,*/ String type, String referenceNumber, LocalDate bookingDate, LocalDate endDate, double amount) {
        this.startDate = startDate;
        //this.user = user;
        this.destination = destination;
        this.hotel = hotel;
        this.flight = flight;
        //this.payment = payment;
        //this.trip = trip;
        this.type = type;
        this.referenceNumber = referenceNumber;
        this.bookingDate = bookingDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /*public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }*/

    /*public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }*/

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

    /*public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

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
