package com.travelapp.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "rooms")
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private String id;
    //private String userid;
    private int roomNumber;
    private double pricePerNight;
    private List<String> amenities;
    private boolean availability;

    private LocalDate BookedAt;
    private LocalDate BookedUntil;
}
