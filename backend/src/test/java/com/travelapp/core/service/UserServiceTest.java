package com.travelapp.core.service;

import com.travelapp.core.model.User;
import com.travelapp.core.repository.UserRepository;
import com.travelapp.rest.dto.UserDTO;
import com.travelapp.rest.dto.UserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User existingUser;
    private UserRequestDTO payload;

    @BeforeEach
    void setUp() {
        existingUser = new User();
        existingUser.setId("1");
        existingUser.setEmail("taro@gmail.com");
        existingUser.setUsername("taro@gmail.com");
        existingUser.setFirstName("Taro");
        existingUser.setLastName("Yamada");
        existingUser.setBalance(500.0);

        payload = new UserRequestDTO();
        payload.setEmail("taro@gmail.com");
        payload.setUsername("taro@gmail.com");
        payload.setFirstName("Taro");
        payload.setLastName("Yamada");
        payload.setPassword("newpassword");
        payload.setBalance(300.0);
    }

    @Test
    void updateUser_Success() throws Exception {
        // Arrange
        when(userRepository.findById("1")).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UserDTO updatedUserDto = userService.updateUser("1", payload);

        // Assert: Check that updatedUserDto reflects the payload changes.
        assertNotNull(updatedUserDto);
        assertEquals("taro@gmail.com", updatedUserDto.getEmail());
        // You can add more assertions depending on your update logic.
    }

    @Test
    void updateUser_UserNotFound_ThrowsException() {
        // Arrange
        when(userRepository.findById("1")).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            userService.updateUser("1", payload);
        });
        assertEquals("Cannot find user with the provided userId", exception.getMessage());
    }

    @Test
    void addFundsToUser_Success() {
        // Arrange
        when(userRepository.findByUsername("taro@gmail.com")).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        // Act
        userService.addFundsToUser("taro@gmail.com", 100.0);

        // Assert: Balance should increase by 100.
        assertEquals(600.0, existingUser.getBalance());
    }
}
