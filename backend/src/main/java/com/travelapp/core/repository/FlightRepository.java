package com.travelapp.core.repository;

import com.travelapp.core.model.Booking;
import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Flight;
import com.travelapp.core.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends MongoRepository<Flight, String> {

    public List<Flight> findAllByairline(String airline);
    public List<Flight> findAllBydepartureAirport(String Type, String referenceNumber);
    //List<Flight> findByUserId(User user);
    public List<Flight> findFlightByDepartureAirportAndArrivalAirport(String departureAirport, String arrivalAirport);
}
