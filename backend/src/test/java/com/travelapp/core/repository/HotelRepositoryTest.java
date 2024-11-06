package com.travelapp.core.repository;

import com.travelapp.core.model.Booking;
import com.travelapp.core.model.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class HotelRepositoryTest {
    @Autowired
    private HotelRepository hotelRepository;


    @Test
    public void shouldReturnAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();


        Assertions.assertEquals(5, hotels.size());
        //Assertions.assertEquals(50, bookings.get(0).getAmount());
    }


    /*@Test
    public void shouldFindBookingByNumber() {
        List<Booking> booking = bookingRepository.findAllByReferenceNumber("bogus bogus");
        Assertions.assertNotNull(booking.get(0));
    }*/
}
