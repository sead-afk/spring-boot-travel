package com.travelapp.core.service;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Vehicle;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.model.enums.VehicleType;
import com.travelapp.core.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> filter(VehicleType vehicleType, String name) {
        return vehicleRepository.findAllByVehicleTypeOrNameLike(vehicleType, name);
    }
}
