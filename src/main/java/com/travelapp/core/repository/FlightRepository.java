package com.travelapp.core.repository;

import com.travelapp.core.model.Booking;
import com.travelapp.core.model.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {

    public List<Flight> findAllByairline(String airline);
    public List<Flight> findAllBydepartureAirport(String Type, String referenceNumber);
    List<Flight> findByUserId(String userId);
    public List<Flight> findFlightByDepartureAirportAndArrivalAirport(String departureAirport, String arrivalAirport);
}
