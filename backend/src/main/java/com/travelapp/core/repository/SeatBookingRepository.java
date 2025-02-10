package com.travelapp.core.repository;

import com.travelapp.core.model.RoomBooking;
import com.travelapp.core.model.SeatBooking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeatBookingRepository extends MongoRepository<SeatBooking, String> {

    public SeatBooking findSeatBookingByTicketIdAndFlightIdOrderByCreatedAtDesc(String ticketId, String flightId);
}
