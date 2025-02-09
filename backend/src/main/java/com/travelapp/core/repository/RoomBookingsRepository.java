package com.travelapp.core.repository;

import com.travelapp.core.model.RoomBooking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomBookingsRepository extends MongoRepository<RoomBooking, String> {

    public RoomBooking findRoomBookingByRoomIdAndHotelIdOrderByCreatedAtDesc(String roomId, String hotelId);
}
