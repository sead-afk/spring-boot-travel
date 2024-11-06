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
public class Hotel {
    @Id
    private String id;
    //private String userid;
    private String name;
    private String location;
    private String description;
    private List<String> amenities;

}
