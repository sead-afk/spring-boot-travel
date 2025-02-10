package com.travelapp.core.service;

import com.travelapp.core.model.*;
import com.travelapp.core.repository.HotelRepository;
import com.travelapp.core.repository.RoomBookingsRepository;
import com.travelapp.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final BookingService bookingService;

    private final RoomBookingsRepository roomBookingsRepository;

    public HotelService(HotelRepository hotelRepository, BookingService bookingService, RoomBookingsRepository roomBookingsRepository) {
        this.hotelRepository = hotelRepository;
        this.bookingService = bookingService;
        this.roomBookingsRepository = roomBookingsRepository;
    }

    @Transactional
    public Booking bookRoom(Booking booking) throws Exception {
        // Extract parameters from the Booking object
        String hotelId = booking.getResourceid();
        String roomId = booking.getDetails();
        LocalDate startDate = booking.getStartDate();
        LocalDate endDate = booking.getEndDate();
        double amount = booking.getAmount();

        // Retrieve the hotel document by hotelId
//        Hotel hotel = hotelRepository.findById(hotelId)
//                .orElseThrow(() -> new RuntimeException("Hotel not found with ID: " + hotelId));

        // Find the specific room in the hotel's list of rooms by matching roomId
//        Room roomToBook = hotel.getRooms().stream()
//                .filter(room -> room.getId() != null && room.getId().equals(roomId))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));


        RoomBooking roomToBook = roomBookingsRepository.findRoomBookingByRoomIdAndHotelIdOrderByCreatedAtDesc(roomId,hotelId);

        // Check if the room is available
        if ( roomToBook != null && !roomToBook.isAvailable(startDate,endDate)) {
            throw new IllegalArgumentException("Room is not available for booking.");
        }

        RoomBooking newEntry = new RoomBooking();
        newEntry.setRoomId(roomId);
        newEntry.setHotelId(hotelId);
        newEntry.setBookedAt(startDate);
        newEntry.setBookedUntil(endDate);
        newEntry.setCreatedAt(LocalDate.now());

        roomBookingsRepository.save(newEntry);
        // Mark the room as booked (set availability to false) and save the hotel document
        //roomToBook.setAvailability(false);

        //hotelRepository.save(hotel);

        // Set the booking date to now
        booking.setBookingDate(LocalDate.now());
        // Delegate to the generic addBooking method to check balance and save the booking
        return bookingService.addBooking(booking);
    }

    /*public List<Hotel> getCurrentUserHotels() {  //my booked Hotels
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        return hotelRepository.findByUserid(user);
    }*/

    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    public Hotel addHotel(Hotel hotel) {
//        var username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
//        //User user = userRepository.findByUsername(username);
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//        User user = optionalUser.orElse(new User());
        //hotel.setUserid(user.getId());
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(String hotelId) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (!hotelOptional.isPresent()) {
            throw new IllegalStateException("Hotel does not exist");
        }
        hotelRepository.deleteById(hotelId);
    }

    public Hotel updateHotel(String hotelId, Hotel payload) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(hotel.isEmpty())
            throw new Exception("Cannot find hotel with provided payload");

        hotel.get().setName(payload.getName());
        hotel.get().setLocation(payload.getLocation());
        hotel.get().setDescription(payload.getDescription());
        hotel.get().setAmenities(payload.getAmenities());

        hotelRepository.save(hotel.get());
        return hotel.get();
    }

    public Hotel getHotelById(String hotelId) throws Exception {
        return hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel not found with ID: " + hotelId));
    }

    public List<Room> getRoomsByHotelId(String hotelId) {
        Hotel hotel = null;
        try {
            hotel = getHotelById(hotelId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return hotel.getRooms();
    }

//    public Room getRoomById(String roomId) {
//        Hotel hotel = hotelRepository.findHotelByRoomId(roomId);
//        if (hotel == null) {
//            throw new RuntimeException("Room not found with ID: " + roomId);
//        }
//        return hotel.getRooms().stream()
//                .filter(room -> room.getId().equals(roomId))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + roomId));
//    }
    /*public List<Hotel> filter(Double price, String location) {
        return hotelRepository.findHotelByPricePerNightGreaterThanAndLocation(price, location);
    }*/
}
