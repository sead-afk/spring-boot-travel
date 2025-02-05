package com.travelapp.core.service;

import com.travelapp.core.model.User;
import com.travelapp.core.repository.UserRepository;
import com.travelapp.rest.dto.LoginRequestDTO;
import com.travelapp.rest.dto.LoginDTO;
import com.travelapp.rest.dto.UserRequestDTO;
import com.travelapp.rest.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Reset mocks if needed (MockitoExtension does that automatically)
    }

    @Test
    void signUp_Success() {
        // Arrange
        UserRequestDTO dto = new UserRequestDTO();
        dto.setEmail("taro@gmail.com");
        dto.setUsername("taro@gmail.com");
        dto.setFirstName("Taro");
        dto.setLastName("Yamada");
        dto.setPassword("rawPassword");
        dto.setBalance(500.0);

        when(userRepository.findUserByEmail("taro@gmail.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("rawPassword")).thenReturn("encodedPassword");
        User userToSave = dto.toEntity();
        userToSave.setPassword("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User saved = invocation.getArgument(0);
            saved.setId("1");
            return saved;
        });

        // Act
        UserDTO userDto = authService.signUp(dto);

        // Assert
        assertNotNull(userDto);
        assertEquals("taro@gmail.com", userDto.getEmail());
    }

    @Test
    void signIn_Success() {
        // Arrange
        String email = "taro@gmail.com";
        String password = "password";
        LoginRequestDTO loginRequest = new LoginRequestDTO(email, password);
        User user = new User();
        user.setEmail(email);
        user.setUsername(email);
        user.setPassword("encodedPassword");

        // Simulate successful authentication; we assume authenticationManager.authenticate() returns a token.
//        doNothing().when(authenticationManager)
//                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        when(userRepository.findUserByEmail(loginRequest.getEmail())).thenReturn(Optional.of(user));

        when(jwtService.generateToken(any(Map.class), any())).thenReturn("jwt-token");

        // Act
        LoginDTO loginDto = authService.signIn(loginRequest);

        // Assert
        assertNotNull(loginDto);
        assertEquals("jwt-token", loginDto.getJwt());
    }

    @Test
    void signIn_InvalidCredentials_ThrowsException() {
        // Arrange
        String email = "taro@gmail.com";
        String password = "wrongPassword";
        LoginRequestDTO loginRequest = new LoginRequestDTO(email, password);

        doThrow(new BadCredentialsException("Invalid credentials"))
                .when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            authService.signIn(loginRequest);
        });
        assertEquals("Invalid email or password", exception.getReason());
    }
}

