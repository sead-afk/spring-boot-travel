package com.travelapp.core.model;

import com.travelapp.core.model.enums.VehicleType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Vehicle {
    @Id
    public String id;
    public String name;
    public Date manufactorDate;
    public VehicleType vehicleType;
    public float mileage;

    public Vehicle(String id, String name, Date manufactorDate, VehicleType vehicleType, float mileage) {
        this.id = id;
        this.name = name;
        this.manufactorDate = manufactorDate;
        this.vehicleType = vehicleType;
        this.mileage = mileage;
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

    public Date getManufactorDate() {
        return manufactorDate;
    }

    public void setManufactorDate(Date manufactorDate) {
        this.manufactorDate = manufactorDate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }
}
