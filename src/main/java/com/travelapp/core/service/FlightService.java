package com.travelapp.core.service;

import com.travelapp.core.model.Flight;
import com.travelapp.core.model.User;
import com.travelapp.core.repository.FlightRepository;
import com.travelapp.core.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private UserRepository userRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getCurrentUserFlights() {  //my booked flights
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        return flightRepository.findByUserId(user.getId());
    }

    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlight(Flight payload) {
        Optional<Flight> flightOptional = flightRepository.findById(payload.getId());
        if (!flightOptional.isPresent()) {
            throw new IllegalStateException("Flight does not exist");
        }
        flightRepository.deleteById(payload.getId());
    }

    public Flight updateFlight(String flightId, Flight payload) throws Exception {
        Optional<Flight> flight = flightRepository.findById(flightId);
        if(flight.isEmpty())
            throw new Exception("Cannot find flight with provided payload");

        flight.get().setAirline(payload.getAirline());
        flight.get().setFlightNumber(payload.getFlightNumber());
        flight.get().setDepartureAirport(payload.getDepartureAirport());
        flight.get().setArrivalAirport(payload.getArrivalAirport());
        flight.get().setDepartureTime(payload.getDepartureTime());
        flight.get().setArrivalTime(payload.getArrivalTime());
        flight.get().setPrice(payload.getPrice());

        flightRepository.save(flight.get());
        return flight.get();
    }

    public Flight getFlightById(String flightId) throws Exception {
        Optional<Flight> flight = flightRepository.findById(flightId);
        if(flight.isEmpty())
            throw new Exception("Cannot find flight with provided payload");

        return flight.get();
    }

    public List<Flight> filter(String departureAirport, String arrivalAirport) {
        return flightRepository.findFlightByDepartureAirportAndArrivalAirport(departureAirport, arrivalAirport);
    }
}
