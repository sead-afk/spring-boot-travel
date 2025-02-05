package com.travelapp.core.service;

import com.travelapp.core.model.Booking;
import com.travelapp.core.model.User;
import com.travelapp.core.repository.BookingRepository;
import com.travelapp.core.repository.UserRepository;
import com.travelapp.rest.dto.UserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(securityContext.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken(userDetails, null));
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void addBooking_SufficientFunds_Success() throws Exception {
        // Arrange
        Booking booking = new Booking();
        booking.setAmount(100.0);
        // booking.setUsername will be ignored in the service since we get it from the security context

        String username = "taro@gmail.com";
        when(userDetails.getUsername()).thenReturn(username);

        User user = new User();
        user.setEmail(username);
        user.setUsername(username);
        user.setBalance(200.0);

        when(userService.getUserProfile(username)).thenReturn(user);

        // Simulate updateUser by returning a DTO (not used in further logic in this test)
        //doNothing().when(userService).updateUser(anyString(), any(UserRequestDTO.class));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        // Act
        Booking result = bookingService.addBooking(booking);

        // Assert: Check that booking was saved and user balance was deducted
        assertNotNull(result);
        // After deduction, balance should be 100 (200 - 100)
        assertEquals(100.0, user.getBalance());
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    void addBooking_InsufficientFunds_ThrowsException() {
        // Arrange
        Booking booking = new Booking();
        booking.setAmount(300.0);

        String username = "taro@gmail.com";
        when(userDetails.getUsername()).thenReturn(username);

        User user = new User();
        user.setEmail(username);
        user.setUsername(username);
        user.setBalance(220.0);

        when(userService.getUserProfile(username)).thenReturn(user);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bookingService.addBooking(booking);
        });
        assertEquals("Insufficient balance to complete the booking.", exception.getMessage());
    }
}
