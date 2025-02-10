package com.travelapp.core.service;

import com.travelapp.core.model.Booking;
import com.travelapp.core.model.RoomBooking;
import com.travelapp.core.model.SeatBooking;
import com.travelapp.core.model.User;
import com.travelapp.core.repository.BookingRepository;
import com.travelapp.core.repository.RoomBookingsRepository;
import com.travelapp.core.repository.SeatBookingRepository;
import com.travelapp.core.repository.UserRepository;
import com.travelapp.rest.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private UserService userService;
    private final BookingRepository bookingRepository;
    private final RoomBookingsRepository roomBookingsRepository;
    @Autowired
    private SeatBookingRepository seatBookingRepository;


    public BookingService(BookingRepository bookingRepository, UserService userService, RoomBookingsRepository roomBookingsRepository) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.roomBookingsRepository = roomBookingsRepository;
    }

    /*public List<Booking> getCurrentUserBookings() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        return bookingRepository.findByUserId(user.getId());
    }*/

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Booking addBooking(Booking booking) throws Exception {
        // Retrieve the username from the authentication context
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        // Retrieve the user based on the username (email)
        User user = userService.getUserProfile(username);

        // Check if the user has sufficient balance
        if (user.getBalance() < booking.getAmount()) {
            throw new IllegalArgumentException("Insufficient balance to complete the booking.");
        }

        // Deduct the amount from the user's account balance
        user.setBalance(user.getBalance() - booking.getAmount());
        var userDto = new UserRequestDTO(user);
        // Update the user with the new balance
        userService.updateUser(user.getId(),userDto);

        // Now, create and save the booking
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(String bookingId, Booking updatedBooking) throws Exception {
        Booking existing = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Check that the booking is upcoming (e.g. startDate is in the future)
        if (existing.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot edit past bookings");
        }

        // Update the fields (you can decide which fields are editable)
        existing.setStartDate(updatedBooking.getStartDate());
        existing.setEndDate(updatedBooking.getEndDate());
        existing.setAmount(updatedBooking.getAmount());
        // Optionally update details if allowed (e.g., room or seat selection)
        existing.setDetails(updatedBooking.getDetails());

        // You might also need to check if the room/ticket is still available for the new dates, etc.
        // For simplicity, we assume the update is valid.
        return bookingRepository.save(existing);
    }

    public void deleteBooking(String bookingId) throws Exception {
        Booking existing = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        // You might restrict deletion only to upcoming bookings
        if (existing.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot delete past bookings");
        }
        String type = existing.getType();
        if(type.equals("HOTEL")) {
            String roomId = existing.getDetails();
            String hotelId = existing.getResourceid();
            RoomBooking roomBooking = roomBookingsRepository.findRoomBookingByRoomIdAndHotelIdOrderByCreatedAtDesc(roomId, hotelId);
            // Optionally, restore room/ticket availability if needed
            roomBookingsRepository.delete(roomBooking);
        }else if(type.equals("FLIGHT")) {
            String flightId = existing.getResourceid();
            String ticketId = existing.getDetails();
            SeatBooking seatBooking = seatBookingRepository.findSeatBookingByTicketIdAndFlightIdOrderByCreatedAtDesc(ticketId, flightId);
            seatBookingRepository.delete(seatBooking);
        }
        bookingRepository.delete(existing);
    }

    public Booking getBookingById(String bookingId) throws Exception {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if(booking.isEmpty())
            throw new Exception("Cannot find booking with provided payload");

        return booking.get();
    }

    /*public List<Booking> filter(String Type, String referenceNumber) {
        return bookingRepository.findAllByTypeOrReferenceNumberLike(Type, referenceNumber);
    }*/

    public List<Booking> getBookingsByUsername(String username)
    {

        var data = bookingRepository.findByUsername(username);
        return data; // Assuming this method exists in the repository
    }

}
