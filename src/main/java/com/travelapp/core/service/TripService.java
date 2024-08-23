package com.travelapp.core.service;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Trip;
import com.travelapp.core.model.Trip;
import com.travelapp.core.repository.TripRepository;
import com.travelapp.core.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public Trip updateTrip(String tripId, Trip payload) throws Exception {
        Optional<Trip> trip = tripRepository.findById(tripId);
        if(trip.isEmpty())
            throw new Exception("Cannot find trip with provided payload");

        trip.get().setUser(payload.getUser());
        trip.get().setDestination(payload.getDestination());
        trip.get().setStartDate(payload.getStartDate());
        trip.get().setEndDate(payload.getEndDate());
        trip.get().setEndDate(payload.getEndDate());
        trip.get().setStatus(payload.getStatus());

        tripRepository.save(trip.get());
        return trip.get();
    }

    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    public List<Trip> filter(Destination destination) {
        return tripRepository.findAllByDestination(destination);
    }

    public void deleteTrip(Trip payload) {
        Optional<Trip> tripOptional = tripRepository.findById(payload.getId());
        if (!tripOptional.isPresent()) {
            throw new IllegalStateException("Trip does not exist");
        }
        tripRepository.deleteById(payload.getId());
    }

    public Trip getTripById(String tripId) throws Exception {
        Optional<Trip> trip = tripRepository.findById(tripId);
        if(trip.isEmpty())
            throw new Exception("Cannot find trip with provided payload");

        return trip.get();
    }
}
