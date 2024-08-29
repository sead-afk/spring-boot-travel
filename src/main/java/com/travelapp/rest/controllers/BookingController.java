package com.travelapp.rest.controllers;

import com.travelapp.core.model.Booking;
import com.travelapp.core.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping(path = "api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping(path = "/add")
    public Booking addBooking(@RequestBody Booking booking){
        return bookingService.addBooking(booking);
    }

    @GetMapping(path = "/")
    public List<Booking> getAll(){
        return bookingService.getBookings();
    }

    @PutMapping(path = "/{bookingId}")
    public Booking updateBooking(@PathVariable("bookingId") String bookingId, @RequestBody Booking bookingpayload) throws Exception {
        return bookingService.updateBooking(bookingId, bookingpayload);
    }

    @DeleteMapping(path = "/{booking}")
    public void deleteBooking(@RequestBody Booking booking) {
        bookingService.deleteBooking(booking);
    }
    @GetMapping(path = "/filter")
    public List<Booking> filterBooking(
            @RequestParam("type") String type,
            @RequestParam("referenceNumber") String referenceNumber
    ){
        return bookingService.filter(type, referenceNumber);
    }

    @GetMapping(path = "/{bookingId}")
    public Booking getBookingById(@PathVariable String bookingId) throws Exception {
        return bookingService.getBookingById(bookingId);
    }
}
