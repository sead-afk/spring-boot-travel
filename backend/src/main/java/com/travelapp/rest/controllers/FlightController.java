package com.travelapp.rest.controllers;

import com.travelapp.core.model.*;
import com.travelapp.core.service.FlightService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/flights")
@SecurityRequirement(name = "JWT Security")
@CrossOrigin(origins = "https://travelwithsead.netlify.app", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.OPTIONS})
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    /*@GetMapping("/my-flights")
    @PreAuthorize("hasRole('MEMBER')")
    public List<Flight> getCurrentUserFlights() {    //Booked flights
        return flightService.getCurrentUserFlights();
    }*/

    @PostMapping(path = "/bookTicket")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> bookTicket(@RequestBody Booking booking) {
        try {
            var ticket = flightService.bookTicket(booking);
            return ResponseEntity.ok(ticket);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Flight addFlight(@RequestBody Flight flight){
        return flightService.addFlight(flight);
    }

    @GetMapping(path = {"", "/"})
    //@PreAuthorize("hasAuthority('ADMIN')")
    public List<Flight> getAll(){
        return flightService.getFlights();
    }

    @PutMapping(path = "/update/{flightId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Flight updateFlight(@PathVariable("flightId") String flightId, @RequestBody Flight flightpayload) throws Exception {
        return flightService.updateFlight(flightId, flightpayload);
    }

    @DeleteMapping(path = "/delete/{flightId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteFlight(@PathVariable("flightId")  String flightId) {
        flightService.deleteFlight(flightId);
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
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Flight getFlightById(@PathVariable String flightId) throws Exception {
        return flightService.getFlightById(flightId);
    }

    @GetMapping("/{flightId}/tickets")
    public List<Tickets> getTicketsByHotelId(@PathVariable String flightId) {
        System.out.println("Fetching tickets for hotel ID: " + flightId);
        return flightService.getTicketsByFlightId(flightId);
    }
}
