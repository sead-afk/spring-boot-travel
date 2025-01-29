package com.travelapp.core.repository;

import com.travelapp.core.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    //public List<Booking> findAllByReferenceNumber(String referenceNumber);
    //public List<Booking> findAllByTypeOrReferenceNumberLike(String Type, String referenceNumber);
    //List<Booking> findByUserId(String userId);
    public List<Booking> findBookingBy(String Type, String name);

    List<Booking> findByUsername(String username);
}
