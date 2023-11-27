package com.travelapp.core.repository;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Vehicle;
import com.travelapp.core.model.enums.VehicleType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    public List<Vehicle> findAllByName(String name);
    public List<Vehicle> findAllByVehicleTypeOrNameLike(VehicleType vehicleType, String name);
}
