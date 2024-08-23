package com.travelapp.rest.controllers;

import com.travelapp.core.model.Trip;
import com.travelapp.core.service.TripService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/trips")
@SecurityRequirement(name = "JWT Security")
public class TripController {
    
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping(path = "/add")
    public Trip addTrip(@RequestBody Trip trip){
        return tripService.addTrip(trip);
    }

    @GetMapping(path = "/")
    public List<Trip> getAll(){
        return tripService.getAll();
    }

    @DeleteMapping(path = "/{trip}")
    public void deleteTrip(@RequestBody Trip trip) {
        tripService.deleteTrip(trip);
    }
    @PutMapping(path = "/{tripId}")
    public Trip updateTrip(@PathVariable("tripId") String tripId, @RequestBody Trip tripPayload) throws Exception {
        return tripService.updateTrip(tripId, tripPayload);
    }
    /*@GetMapping(path = "/filter")
    public List<Trip> filterTrips(
            @RequestParam("type") TripType tripType,
            @RequestParam("name") String name
    ){
        return tripService.filter(tripType, name);
    }*/
    @GetMapping(path = "/{tripId}")
    public Trip getTripById(@PathVariable String tripId) throws Exception {
        return tripService.getTripById(tripId);
    }
}
