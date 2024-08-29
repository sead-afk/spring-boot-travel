package com.travelapp.rest.controllers;

import com.travelapp.core.model.Flight;
import com.travelapp.core.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping(path = "/add")
    public Flight addFlight(@RequestBody Flight flight){
        return flightService.addFlight(flight);
    }

    @GetMapping(path = "/")
    public List<Flight> getAll(){
        return flightService.getFlights();
    }

    @PutMapping(path = "/{flightId}")
    public Flight updateFlight(@PathVariable("flightId") String flightId, @RequestBody Flight flightpayload) throws Exception {
        return flightService.updateFlight(flightId, flightpayload);
    }

    @DeleteMapping(path = "/{flight}")
    public void deleteFlight(@RequestBody Flight flight) {
        flightService.deleteFlight(flight);
    }
    @GetMapping(path = "/filter")
    public List<Flight> filterFlight(
            @RequestParam("departureAirport") String departureAirport,
            @RequestParam("arrivalAirport") String arrivalAirport
    ){
        return flightService.filter(departureAirport, arrivalAirport);
    }

    @GetMapping(path = "/{flightId}")
    public Flight getFlightById(@PathVariable String flightId) throws Exception {
        return flightService.getFlightById(flightId);
    }
}
