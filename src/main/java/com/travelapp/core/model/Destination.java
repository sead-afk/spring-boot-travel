package com.travelapp.core.model;

import com.travelapp.core.model.enums.DestinationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Destination {
    @Id
    private String id;
    private String name;
    private DestinationType destinationType;

    public Destination(String id, String name, DestinationType destinationType) {
        this.id = id;
        this.name = name;
        this.destinationType = destinationType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DestinationType getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(DestinationType destinationType) {
        this.destinationType = destinationType;
    }
}
