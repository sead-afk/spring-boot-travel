package com.travelapp.rest.controllers;

import com.travelapp.core.model.Flight;
import com.travelapp.core.service.FlightService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/flight")
@SecurityRequirement(name = "JWT Security")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Flight addFlight(@RequestBody Flight flight){
        return flightService.addFlight(flight);
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Flight> getAll(){
        return flightService.getFlights();
    }

    @PutMapping(path = "/{flightId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Flight updateFlight(@PathVariable("flightId") String flightId, @RequestBody Flight flightpayload) throws Exception {
        return flightService.updateFlight(flightId, flightpayload);
    }

    @DeleteMapping(path = "/{flight}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteFlight(@RequestBody Flight flight) {
        flightService.deleteFlight(flight);
    }
    @GetMapping(path = "/filter")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Flight> filterFlight(
            @RequestParam("departureAirport") String departureAirport,
            @RequestParam("arrivalAirport") String arrivalAirport
    ){
        return flightService.filter(departureAirport, arrivalAirport);
    }

    @GetMapping(path = "/{flightId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Flight getFlightById(@PathVariable String flightId) throws Exception {
        return flightService.getFlightById(flightId);
    }
}
