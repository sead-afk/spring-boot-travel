package com.travelapp.core.service;

import com.travelapp.core.model.Booking;
import com.travelapp.core.model.User;
import com.travelapp.core.repository.BookingRepository;
import com.travelapp.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private UserService userService;
    private final BookingRepository bookingRepository;
    private UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    /*public List<Booking> getCurrentUserBookings() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        return bookingRepository.findByUserId(user.getId());
    }*/

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Booking addBooking(Booking booking) {
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

        // Update the user with the new balance
        userService.updateUser(user);

        // Now, create and save the booking
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Booking payload) {
        Optional<Booking> bookingOptional = bookingRepository.findById(payload.getId());
        if (!bookingOptional.isPresent()) {
            throw new IllegalStateException("Booking does not exist");
        }
        bookingRepository.deleteById(payload.getId());
    }

    public Booking updateBooking(String bookingId, Booking payload) throws Exception {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if(booking.isEmpty())
            throw new Exception("Cannot find booking with provided payload");

        //booking.get().setUser(payload.getUser());
        booking.get().setType(payload.getType());
        //booking.get().setReferenceNumber(payload.getReferenceNumber());
        booking.get().setBookingDate(payload.getBookingDate());
        booking.get().setStartDate(payload.getStartDate());
        booking.get().setEndDate(payload.getEndDate());
        booking.get().setUsername(payload.getUsername());
        booking.get().setResourceid(payload.getResourceid());
        booking.get().setDetails(payload.getDetails());

        bookingRepository.save(booking.get());
        return booking.get();
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
