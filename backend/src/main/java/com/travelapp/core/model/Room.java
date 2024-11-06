package com.travelapp.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private String id;
    //private String userid;
    private int roomNumber;
    private Hotel hotel;
    private String description;
    private double pricePerNight;
    private List<String> amenities;
}
