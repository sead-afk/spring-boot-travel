//package com.travelapp.core.model;
//
//import org.assertj.core.api.AssertionsForInterfaceTypes;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@SpringBootTest
//public class HotelTest {
//
//    @Test
//    void shouldCreateNewHotel() {
//        List list = new ArrayList<String>(){{
//            add("pool");
//            add("restaurant");
//            add("rooms");
//        }};
//        Hotel hotel = new Hotel("id12211",
//                "Messi",
//                "Spain",
//                "egzotican i lijep",
//                list);
//
//        Assertions.assertEquals("id12211", hotel.getId());
//        Assertions.assertEquals("Messi", hotel.getName());
//        Assertions.assertEquals("Spain", hotel.getLocation());
//        Assertions.assertEquals("egzotican i lijep", hotel.getDescription());
//        Assertions.assertEquals(list, hotel.getAmenities());
//
//    }
//
//    /*@Test
//    void shouldCompareTwoBooks() {
//        User jeff = new User();
//        Booking booking1 = new Booking(
//                "someId123",
//                jeff,
//                "Test Hotel",
//                "90678",
//                LocalDate.of(2015, 1, 15),
//                LocalDate.of(2015, 4, 15),
//                LocalDate.of(2015, 5, 15),
//                20
//        );
//
//
//        Booking booking2 = new Booking(
//                "someId123",
//                jeff,
//                "Test Hotel",
//                "90678",
//                LocalDate.of(2015, 1, 15),
//                LocalDate.of(2015, 4, 15),
//                LocalDate.of(2015, 5, 15),
//                20
//        );
//
//        AssertionsForInterfaceTypes
//                .assertThat(booking1)
//                .usingRecursiveComparison()
//                .isEqualTo(booking2);
//    }*/
//}
