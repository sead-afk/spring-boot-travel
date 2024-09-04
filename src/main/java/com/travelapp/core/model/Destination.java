package com.travelapp.core.model;

import com.travelapp.core.model.enums.DestinationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Destination {
    @Id
    private String id;
    private String userId;
    private String name;
    private String country;
    private List<String> images;
    private DestinationType destinationType;

    /*public Destination(String id, String name, DestinationType destinationType) {
        this.id = id;
        this.name = name;
        this.destinationType = destinationType;
    }*/

    public Destination(String id, DestinationType destinationType, List<String> images, String name, String country) {
        this.id = id;
        this.destinationType = destinationType;
        this.images = images;
        this.name = name;
        this.country = country;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
