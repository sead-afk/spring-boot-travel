package com.travelapp.core.service;

import com.travelapp.core.model.Hotel;
import com.travelapp.core.model.Room;
import com.travelapp.core.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class HotelServiceTest {

    @InjectMocks
    private HotelService hotelService;

    @Mock
    private HotelRepository hotelRepository;

    private Hotel dummyHotel;

    @BeforeEach
    void setUp() {
        dummyHotel = new Hotel();
        dummyHotel.setId("hotel1");
        dummyHotel.setName("Seaside Resort");
        dummyHotel.setLocation("Beach City");
        dummyHotel.setDescription("A lovely seaside hotel");
        // Set up dummy rooms if needed
        dummyHotel.setRooms(Arrays.asList(
                new Room("room1", 101, 150.0, Collections.singletonList("TV"), true),
                new Room("room2", 102, 150.0, Collections.singletonList("WiFi"), true)
        ));
    }

    @Test
    void getHotels_ReturnsList() {
        // Arrange
        List<Hotel> hotels = Arrays.asList(dummyHotel);
        when(hotelRepository.findAll()).thenReturn(hotels);

        // Act
        List<Hotel> result = hotelService.getHotels();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Seaside Resort", result.get(0).getName());
    }

    @Test
    void addHotel_Success() {
        // Arrange
        when(hotelRepository.save(any(Hotel.class))).thenReturn(dummyHotel);

        // Act
        Hotel savedHotel = hotelService.addHotel(dummyHotel);

        // Assert
        assertNotNull(savedHotel);
        assertEquals("Seaside Resort", savedHotel.getName());
    }

    @Test
    void deleteHotel_HotelExists_Success() {
        // Arrange
        when(hotelRepository.findById("hotel1")).thenReturn(Optional.of(dummyHotel));

        // Act
        hotelService.deleteHotel("hotel1");

        // Assert: Verify that deleteById is called once.
        verify(hotelRepository, times(1)).deleteById("hotel1");
    }

    @Test
    void deleteHotel_HotelDoesNotExist_ThrowsException() {
        // Arrange
        when(hotelRepository.findById("hotel1")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            hotelService.deleteHotel("hotel1");
        });
        assertEquals("Hotel does not exist", exception.getMessage());
    }

    @Test
    void updateHotel_Success() throws Exception {
        // Arrange
        Hotel updatedHotelPayload = new Hotel();
        updatedHotelPayload.setName("Mountain Retreat");
        updatedHotelPayload.setLocation("Highlands");
        updatedHotelPayload.setDescription("A relaxing mountain getaway");
        updatedHotelPayload.setAmenities(Collections.singletonList("Spa"));

        when(hotelRepository.findById("hotel1")).thenReturn(Optional.of(dummyHotel));
        when(hotelRepository.save(any(Hotel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Hotel result = hotelService.updateHotel("hotel1", updatedHotelPayload);

        // Assert
        assertNotNull(result);
        assertEquals("Mountain Retreat", result.getName());
        assertEquals("Highlands", result.getLocation());
        assertEquals("A relaxing mountain getaway", result.getDescription());
        assertEquals(Collections.singletonList("Spa"), result.getAmenities());
    }

    @Test
    void getHotelById_HotelExists_Success() throws Exception {
        // Arrange
        when(hotelRepository.findById("hotel1")).thenReturn(Optional.of(dummyHotel));

        // Act
        Hotel result = hotelService.getHotelById("hotel1");

        // Assert
        assertNotNull(result);
        assertEquals("Seaside Resort", result.getName());
    }

    @Test
    void getHotelById_HotelDoesNotExist_ThrowsException() {
        // Arrange
        when(hotelRepository.findById("hotel1")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            hotelService.getHotelById("hotel1");
        });
        assertTrue(exception.getMessage().contains("Hotel not found"));
    }

    @Test
    void getRoomsByHotelId_ReturnsRooms() {
        // Arrange
        when(hotelRepository.findById("hotel1")).thenReturn(Optional.of(dummyHotel));

        // Act
        List<Room> rooms = hotelService.getRoomsByHotelId("hotel1");

        // Assert
        assertNotNull(rooms);
        assertEquals(2, rooms.size());
    }
}
