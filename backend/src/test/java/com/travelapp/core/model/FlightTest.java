package com.travelapp.core.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void testFlightGettersAndSetters() {
        Flight flight = new Flight();
        flight.setId("f1");
        flight.setAirline("JetAir");
        flight.setFlightNumber("JA123");
        flight.setDepartureAirport("JFK");
        flight.setArrivalAirport("LAX");
        LocalDateTime depTime = LocalDateTime.of(2025, 4, 1, 10, 0);
        LocalDateTime arrTime = LocalDateTime.of(2025, 4, 1, 16, 0);
        flight.setDepartureTime(depTime);
        flight.setArrivalTime(arrTime);
        // For tickets, simply verify assignment (detailed tests would go in TicketsTest)
        Tickets ticket1 = new Tickets("t1", "12A", 200.0);
        Tickets ticket2 = new Tickets("t2", "12B", 200.0);
        flight.setTickets(Arrays.asList(ticket1, ticket2));

        assertEquals("f1", flight.getId());
        assertEquals("JetAir", flight.getAirline());
        assertEquals("JA123", flight.getFlightNumber());
        assertEquals("JFK", flight.getDepartureAirport());
        assertEquals("LAX", flight.getArrivalAirport());
        assertEquals(depTime, flight.getDepartureTime());
        assertEquals(arrTime, flight.getArrivalTime());
        assertNotNull(flight.getTickets());
        assertEquals(2, flight.getTickets().size());
    }
}

