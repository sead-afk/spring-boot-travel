package com.travelapp.core.service;

import com.travelapp.core.model.*;
import com.travelapp.core.repository.FlightRepository;
import com.travelapp.core.repository.SeatBookingRepository;
import com.travelapp.core.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final SeatBookingRepository seatBookingRepository;
    private UserRepository userRepository;
    private final BookingService bookingService;

    public FlightService(FlightRepository flightRepository, BookingService bookingService, SeatBookingRepository seatBookingRepository) {
        this.flightRepository = flightRepository;
        this.bookingService = bookingService;
        this.seatBookingRepository = seatBookingRepository;
    }

    /*public List<Flight> getCurrentUserFlights() {  //my booked flights
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        return flightRepository.findByUserId(user.getId());
    }*/

    @Transactional
    public Booking bookTicket(Booking booking) throws Exception {
        // Extract parameters from the Booking object
        String flightId = booking.getResourceid();
        String ticketId = booking.getDetails();
        double amount = booking.getAmount();

        Flight flight = flightRepository.findById(flightId).orElse(null);

        LocalDate startDate = flight.getDepartureTime().toLocalDate();

        // Retrieve the flight document by flightId
//        Flight flight = flightRepository.findById(flightId)
//                .orElseThrow(() -> new RuntimeException("Flight not found with ID: " + flightId));

        // Find the specific room in the flight's list of rooms by matching ticketId
//        Tickets ticketToBook = flight.getTickets().stream()
//                .filter(ticket -> ticket.getId() != null && ticket.getId().equals(ticketId))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + ticketId));

        // Check if the room is available

        SeatBooking seatToBook = seatBookingRepository.findSeatBookingByTicketIdAndFlightIdOrderByCreatedAtDesc(ticketId, flightId);

        // Check if the room is available
        if ( seatToBook != null && !seatToBook.isAvailable(startDate,ticketId)) {
            throw new IllegalArgumentException("Seat is not available for booking.");
        }

        SeatBooking newEntry = new SeatBooking();
        newEntry.setTicketId(ticketId);
        newEntry.setFlightId(flightId);
        newEntry.setBookedAt(startDate);
        newEntry.setBookingId(booking.getId());
        newEntry.setCreatedAt(LocalDate.now());

        // Mark the room as booked (set availability to false) and save the flight document
//        seatToBook.setAvailability(false);
        // Set the booking date to now
        booking.setBookingDate(LocalDate.now());

        seatBookingRepository.save(newEntry);

        // Delegate to the generic addBooking method to check balance and save the booking
        return bookingService.addBooking(booking);
    }
    public List<Flight> getFlights() {
        return flightRepository.findAll();
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlight(String flightId) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        if (!flightOptional.isPresent()) {
            throw new IllegalStateException("Flight does not exist");
        }
        flightRepository.deleteById(flightId);
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

    public List<Tickets> getTicketsByFlightId(String flightId) {
        Flight flight = null;
        try {
            flight = getFlightById(flightId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flight.getTickets();
    }
}
