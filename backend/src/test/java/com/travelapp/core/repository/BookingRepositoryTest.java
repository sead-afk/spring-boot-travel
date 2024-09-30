package com.travelapp.core.repository;

import com.travelapp.core.model.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;


    @Test
    public void shouldReturnAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();


        Assertions.assertEquals(0, bookings.size());
        Assertions.assertEquals(50, bookings.get(0).getAmount());
    }


    @Test
    public void shouldFindBookingByNumber() {
        List<Booking> booking = bookingRepository.findAllByReferenceNumber("bogus bogus");
        Assertions.assertNotNull(booking.get(0));
    }
}


