package com.travelapp.core.repository;

import com.travelapp.core.model.RoomBooking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoomBookingsRepository extends MongoRepository<RoomBooking, String> {

    public RoomBooking findRoomBookingByRoomIdAndHotelIdOrderByCreatedAtDesc(String roomId, String hotelId);
    public RoomBooking findRoomBookingByRoomIdAndHotelIdAndBookingIdOrderByCreatedAtDesc(String roomId, String hotelId,String bookingId);
    public List<RoomBooking> findRoomBookingsByRoomIdAndHotelIdOrderByCreatedAtDesc(String roomId, String hotelId);
}
