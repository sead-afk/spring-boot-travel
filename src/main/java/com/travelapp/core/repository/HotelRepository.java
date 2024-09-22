package com.travelapp.core.repository;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Flight;
import com.travelapp.core.model.Hotel;
import com.travelapp.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {
    public List<Hotel> findAllByLocation(String location);
    public List<Hotel> findAllByDescriptionLike(String description);
    List<Hotel> findByUserid(User user);
    public List<Hotel> findHotelByPricePerNightGreaterThanAndLocation(Double price, String location);
}
