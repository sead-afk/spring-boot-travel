package com.travelapp.rest.controllers;

import com.travelapp.core.model.Booking;
import com.travelapp.core.service.BookingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping(path = "api/v1/booking")
@SecurityRequirement(name = "JWT Security")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/my-bookings")
    @PreAuthorize("hasRole('MEMBER')")
    public List<Booking> getCurrentUserBookings() {
        return bookingService.getCurrentUserBookings();
    }

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Booking addBooking(@RequestBody Booking booking){
        return bookingService.addBooking(booking);
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Booking> getAll(){
        return bookingService.getBookings();
    }

    @PutMapping(path = "/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Booking updateBooking(@PathVariable("bookingId") String bookingId, @RequestBody Booking bookingpayload) throws Exception {
        return bookingService.updateBooking(bookingId, bookingpayload);
    }

    @DeleteMapping(path = "/{booking}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteBooking(@RequestBody Booking booking) {
        bookingService.deleteBooking(booking);
    }
    @GetMapping(path = "/filter")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Booking> filterBooking(
            @RequestParam("type") String type,
            @RequestParam("referenceNumber") String referenceNumber
    ){
        return bookingService.filter(type, referenceNumber);
    }

    @GetMapping(path = "/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Booking getBookingById(@PathVariable String bookingId) throws Exception {
        return bookingService.getBookingById(bookingId);
    }
}
