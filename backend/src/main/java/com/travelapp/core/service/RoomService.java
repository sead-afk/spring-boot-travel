package com.travelapp.core.service;

import com.travelapp.core.model.Room;
import com.travelapp.core.model.User;
import com.travelapp.core.repository.RoomRepository;
import com.travelapp.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    /*public List<Room> getCurrentUserRooms() {  //my booked Rooms
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        return roomRepository.findByUserid(user);
    }*/

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Room addRoom(Room room) {
        /*var username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        //room.setUserId(user.getId());
        room.setUserid(user.getId());*/     //try and catch error?
        return roomRepository.save(room);
    }

    public void deleteRoom(String roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        if (!roomOptional.isPresent()) {
            throw new IllegalStateException("Room does not exist");
        }
        roomRepository.deleteById(roomId);
    }

    public Room updateRoom(String roomId, Room payload) throws Exception {
        Optional<Room> room = roomRepository.findById(roomId);
        if(room.isEmpty())
            throw new Exception("Cannot find room with provided payload");

        room.get().setRoomNumber(payload.getRoomNumber());
        room.get().setHotel(payload.getHotel());
        room.get().setDescription(payload.getDescription());
        room.get().setAmenities(payload.getAmenities());
        room.get().setAmenities(payload.getAmenities());

        roomRepository.save(room.get());
        return room.get();
    }

    public Room getRoomById(String roomId) throws Exception {
        Optional<Room> room = roomRepository.findById(roomId);
        if(room.isEmpty())
            throw new Exception("Cannot find room with provided payload");

        return room.get();
    }

    public Room getRoomByNumber(int roomNumber) throws Exception {
        Optional<Room> room = roomRepository.findByRoomNumber(roomNumber);
        if(room.isEmpty())
            throw new Exception("Cannot find room with provided payload");

        return room.get();
    }

    /*public List<Room> filter(Double price, String location) {
        return roomRepository.findRoomByPricePerNightGreaterThanAndLocation(price, location);
    }*/
}
