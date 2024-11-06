package com.travelapp.core.repository;

import com.travelapp.core.model.Booking;
import com.travelapp.core.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
    Optional<Room> findByRoomNumber(int roomNumber);
}
