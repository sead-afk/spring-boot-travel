package com.travelapp.core.controllers;

import com.jayway.jsonpath.JsonPath;
import com.travelapp.core.model.Booking;
import com.travelapp.core.service.BookingService;
import com.travelapp.core.service.JwtService;
import com.travelapp.core.service.UserService;
import com.travelapp.rest.configuration.SecurityConfiguration;
import com.travelapp.rest.controllers.BookingController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

@AutoConfigureMockMvc
@WebMvcTest(BookingController.class)
@Import(SecurityConfiguration.class)
public class BookingControllerTest {

    @Autowired
    MockMvc mockMvc;


    @MockBean
    BookingService bookingService;


    @MockBean
    JwtService jwtService;


    @MockBean
    UserService userService;


    @MockBean
    AuthenticationProvider authenticationProvider;


    @Test
    void shouldReturnAllBookings() throws Exception {


        Booking booking = new Booking();
        booking.setReferenceNumber("11111");
        booking.setType("Hotel");
        booking.setBookingDate(LocalDate.of(2014, 1, 15));


        Mockito.when(bookingService.getBookings()).thenReturn(List.of(booking));


        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();


        String response = result.getResponse().getContentAsString();
        Assertions.assertEquals(1, (Integer) JsonPath.read(response, "$.length()"));
        Assertions.assertEquals("11111", JsonPath.read(response, "$.[0].referenceNumber"));


    }

}
