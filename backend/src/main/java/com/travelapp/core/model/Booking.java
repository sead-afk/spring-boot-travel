package com.travelapp.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;
    private String username;
    //private Destination destination;
    //private bookingType bookingType;
    //private Payment payment;
    //private Trip trip;
    private String resourceid;
    private String details;
    private String type;// e.g., FLIGHT, HOTEL
    //private String referenceNumber;
    private LocalDate bookingDate= LocalDate.now();
    @Nullable
    private LocalDate startDate;
    @Nullable
    private LocalDate endDate;
    private double amount;
}
