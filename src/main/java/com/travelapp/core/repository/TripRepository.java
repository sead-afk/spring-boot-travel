package com.travelapp.core.repository;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Trip;
import com.travelapp.core.model.enums.DestinationType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TripRepository extends MongoRepository<Trip, String> {
    public List<Trip> findAllByDestination(Destination destination);
    List<Trip> findByUserId(String userId);
    //public List<Trip> findAllByDestinationTypeAndNameEquals(DestinationType destinationType, String name);

    //public Destination findFirstByNameEquals(String name);
}
