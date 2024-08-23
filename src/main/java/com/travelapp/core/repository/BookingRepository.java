package com.travelapp.core.repository;

import com.travelapp.core.model.Booking;
import com.travelapp.core.model.Vehicle;
import com.travelapp.core.model.enums.VehicleType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    public List<Booking> findAllByName(String name);
    public List<Booking> findAllByBookingTypeOrNameLike(String Type, String name);
}
