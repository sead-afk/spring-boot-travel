package com.travelapp.core.service;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Vehicle;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.model.enums.VehicleType;
import com.travelapp.core.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Vehicle updateVehicle(String vehicleId, Vehicle payload) throws Exception {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        if(vehicle.isEmpty())
            throw new Exception("Cannot find vehicle with provided payload");

        vehicle.get().setName(payload.getName());
        vehicle.get().setVehicleType(payload.getVehicleType());
        vehicle.get().setMileage(payload.getMileage());
        vehicle.get().setManufactorDate(payload.getManufactorDate());

        vehicleRepository.save(vehicle.get());
        return vehicle.get();
    }
    public List<Vehicle> filter(VehicleType vehicleType, String name) {
        return vehicleRepository.findAllByVehicleTypeOrNameLike(vehicleType, name);
    }
}
