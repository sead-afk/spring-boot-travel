package com.travelapp.core.service;

import com.travelapp.core.model.Flight;
import com.travelapp.core.model.Tickets;
import com.travelapp.core.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class FlightServiceTest {

    @InjectMocks
    private FlightService flightService;

    @Mock
    private FlightRepository flightRepository;

    private Flight dummyFlight;

    @BeforeEach
    void setUp() {
        dummyFlight = new Flight();
        dummyFlight.setId("flight1");
        dummyFlight.setAirline("JetAir");
        dummyFlight.setFlightNumber("JA123");
        dummyFlight.setDepartureAirport("JFK");
        dummyFlight.setArrivalAirport("LAX");
        dummyFlight.setDepartureTime(LocalDateTime.now().plusDays(1));
        dummyFlight.setArrivalTime(LocalDateTime.now().plusDays(1).plusHours(6));
        dummyFlight.setTickets(Arrays.asList(
                new Tickets("ticket1", "12A", true, 200.0),
                new Tickets("ticket2", "12B", true, 200.0)
        ));
    }

    @Test
    void getFlights_ReturnsList() {
        // Arrange
        List<Flight> flights = Arrays.asList(dummyFlight);
        when(flightRepository.findAll()).thenReturn(flights);

        // Act
        List<Flight> result = flightService.getFlights();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("JA123", result.get(0).getFlightNumber());
    }

    @Test
    void addFlight_Success() {
        // Arrange
        when(flightRepository.save(any(Flight.class))).thenReturn(dummyFlight);

        // Act
        Flight savedFlight = flightService.addFlight(dummyFlight);

        // Assert
        assertNotNull(savedFlight);
        assertEquals("flight1", savedFlight.getId());
    }

    @Test
    void deleteFlight_FlightExists_Success() {
        // Arrange
        when(flightRepository.findById("flight1")).thenReturn(Optional.of(dummyFlight));

        // Act
        flightService.deleteFlight("flight1");

        // Assert: Verify deletion method is called once
        verify(flightRepository, times(1)).deleteById("flight1");
    }

    @Test
    void deleteFlight_FlightDoesNotExist_ThrowsException() {
        // Arrange
        when(flightRepository.findById("flight1")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            flightService.deleteFlight("flight1");
        });
        assertEquals("Flight does not exist", exception.getMessage());
    }

    @Test
    void updateFlight_Success() throws Exception {
        // Arrange
        Flight updatePayload = new Flight();
        updatePayload.setAirline("NewAir");
        updatePayload.setFlightNumber("NA456");
        updatePayload.setDepartureAirport("ORD");
        updatePayload.setArrivalAirport("SFO");
        updatePayload.setDepartureTime(LocalDateTime.now().plusDays(2));
        updatePayload.setArrivalTime(LocalDateTime.now().plusDays(2).plusHours(4));

        when(flightRepository.findById("flight1")).thenReturn(Optional.of(dummyFlight));
        when(flightRepository.save(any(Flight.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Flight result = flightService.updateFlight("flight1", updatePayload);

        // Assert
        assertNotNull(result);
        assertEquals("NewAir", result.getAirline());
        assertEquals("NA456", result.getFlightNumber());
        assertEquals("ORD", result.getDepartureAirport());
        assertEquals("SFO", result.getArrivalAirport());
    }

    @Test
    void getFlightById_FlightExists_Success() throws Exception {
        // Arrange
        when(flightRepository.findById("flight1")).thenReturn(Optional.of(dummyFlight));

        // Act
        Flight result = flightService.getFlightById("flight1");

        // Assert
        assertNotNull(result);
        assertEquals("flight1", result.getId());
    }

    @Test
    void getFlightById_FlightDoesNotExist_ThrowsException() {
        // Arrange
        when(flightRepository.findById("flight1")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            flightService.getFlightById("flight1");
        });
        assertTrue(exception.getMessage().contains("Cannot find flight"));
    }

    @Test
    void filter_ReturnsMatchingFlights() {
        // Arrange
        String departureAirport = "JFK";
        String arrivalAirport = "LAX";
        when(flightRepository.findFlightByDepartureAirportAndArrivalAirport(departureAirport, arrivalAirport))
                .thenReturn(Arrays.asList(dummyFlight));

        // Act
        List<Flight> result = flightService.filter(departureAirport, arrivalAirport);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("JFK", result.get(0).getDepartureAirport());
        assertEquals("LAX", result.get(0).getArrivalAirport());
    }

    @Test
    void getTicketsByFlightId_ReturnsTickets() throws Exception {
        // Arrange
        when(flightRepository.findById("flight1")).thenReturn(Optional.of(dummyFlight));

        // Act
        List<Tickets> tickets = flightService.getTicketsByFlightId("flight1");

        // Assert
        assertNotNull(tickets);
        assertEquals(2, tickets.size());
    }
}
