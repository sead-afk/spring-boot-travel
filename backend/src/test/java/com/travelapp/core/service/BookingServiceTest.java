package com.travelapp.core.service;

import com.travelapp.core.model.Booking;
import com.travelapp.core.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
public class BookingServiceTest {

    @MockBean
    BookingRepository bookingRepository;


    @Autowired
    BookingService bookingService;


    @Test
    public void shouldReturnBookingWhenCreated() {
        Booking booking = new Booking();
        booking.setReferenceNumber("11111");
        booking.setType("Hotel");
        booking.setBookingDate(LocalDate.of(2014, 1, 15));


        Mockito.when(bookingRepository.save(ArgumentMatchers.any(Booking.class))).thenReturn(booking);


        Booking savedBooking = bookingService.addBooking(booking);
        assertThat(booking.getReferenceNumber()).isEqualTo(savedBooking.getReferenceNumber());
        assertThat(booking.getType()).isNotNull();
        System.out.println(savedBooking.getId());
    }


    @Test
    public void shouldReturnBookingById() {
        Booking booking = new Booking();
        booking.setId("someMongoId");
        booking.setReferenceNumber("11111");
        booking.setType("Hotel");
        booking.setBookingDate(LocalDate.of(2014, 1, 15));


        Mockito.when(bookingRepository.findById("someMongoId")).thenReturn(Optional.of(booking));


        Booking foundBooking = null;
        try {
            foundBooking = bookingService.getBookingById("someMongoId");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(foundBooking.getReferenceNumber()).isEqualTo("11111");
    }



}
