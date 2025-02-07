package com.travelapp.core.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    void testBookingGettersAndSetters() {
        Booking booking = new Booking();
        booking.setId("b1");
        booking.setUsername("testuser");
        booking.setResourceid("res1");
        booking.setDetails("detail1");
        booking.setType("FLIGHT");
        booking.setBookingDate(LocalDate.of(2025, 2, 2));
        booking.setStartDate(LocalDate.of(2025, 3, 1));
        booking.setEndDate(LocalDate.of(2025, 3, 10));
        booking.setAmount(150.0);

        assertEquals("b1", booking.getId());
        assertEquals("testuser", booking.getUsername());
        assertEquals("res1", booking.getResourceid());
        assertEquals("detail1", booking.getDetails());
        assertEquals("FLIGHT", booking.getType());
        assertEquals(LocalDate.of(2025, 2, 2), booking.getBookingDate());
        assertEquals(LocalDate.of(2025, 3, 1), booking.getStartDate());
        assertEquals(LocalDate.of(2025, 3, 10), booking.getEndDate());
        assertEquals(150.0, booking.getAmount());
    }
}

