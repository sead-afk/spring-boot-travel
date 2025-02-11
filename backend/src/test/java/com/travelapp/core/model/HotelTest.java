package com.travelapp.core.model;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    @Test
    void testHotelGettersAndSetters() {
        Hotel hotel = new Hotel();
        hotel.setId("h1");
        hotel.setName("Seaside Resort");
        hotel.setLocation("Beach City");
        hotel.setDescription("A lovely seaside hotel");
        hotel.setAmenities(Arrays.asList("Pool", "WiFi"));

        // For rooms, you might simply test that it accepts a list.
        Room room1 = new Room("r1", 101, 150.0, Collections.singletonList("TV"));
        Room room2 = new Room("r2", 102, 150.0, Collections.singletonList("WiFi"));
        hotel.setRooms(Arrays.asList(room1, room2));

        assertEquals("h1", hotel.getId());
        assertEquals("Seaside Resort", hotel.getName());
        assertEquals("Beach City", hotel.getLocation());
        assertEquals("A lovely seaside hotel", hotel.getDescription());
        assertEquals(Arrays.asList("Pool", "WiFi"), hotel.getAmenities());
        assertNotNull(hotel.getRooms());
        assertEquals(2, hotel.getRooms().size());
    }
}
