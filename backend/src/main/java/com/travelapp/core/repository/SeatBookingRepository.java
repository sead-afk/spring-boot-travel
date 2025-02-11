package com.travelapp.core.repository;

import com.travelapp.core.model.RoomBooking;
import com.travelapp.core.model.SeatBooking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SeatBookingRepository extends MongoRepository<SeatBooking, String> {

    public SeatBooking findSeatBookingByTicketIdAndFlightIdOrderByCreatedAtDesc(String ticketId, String flightId, String bookingId);
    public List<SeatBooking> findSeatBookingsByTicketIdAndFlightIdOrderByCreatedAtDesc(String ticketId, String flightId);
    public SeatBooking findSeatBookingByTicketIdAndFlightIdOrderByCreatedAtDesc(String ticketId, String flightId);
}
