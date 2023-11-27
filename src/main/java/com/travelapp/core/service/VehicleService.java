package com.travelapp.core.service;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Vehicle;
import com.travelapp.core.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        return VehicleRepository.save(vehicle);
    }
}
