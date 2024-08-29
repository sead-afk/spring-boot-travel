package com.travelapp.core.repository;

import com.travelapp.core.model.Flight;
import com.travelapp.core.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {
    public List<Hotel> findAllByLocation(String location);
    public List<Hotel> findAllByDescriptionLike(String description);
    public List<Hotel> findHotelByPricePerNightGreaterThanAndLocation(Double price, String location);
}
