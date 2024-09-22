package com.travelapp.core.model;

import com.travelapp.core.model.enums.DestinationType;
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
public class Destination {
    @Id
    private String id;
    private String userId;
    private String name;
    private String country;
    private List<String> images;
    private DestinationType destinationType;
}
