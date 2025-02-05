package com.travelapp.core.service;

import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    private final String secretKey = "aVerySecretKeyThatNeedsToBeAtLeast32CharactersLong";

    @BeforeEach
    void setUp() {
        // Set the jwtSigningKey via Reflection (or consider configuring via test properties)
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", secretKey);
    }

    @Test
    void generateTokenAndExtractUsername_Success() {
        // Arrange: create dummy UserDetails (using a simple string as subject)
        String username = "taro@gmail.com";
        Map<String, String> claims = Map.of("email", username);

        // Act: generate token
        String token = jwtService.generateToken(claims, new org.springframework.security.core.userdetails.User(
                username, "dummy", java.util.Collections.emptyList()
        ));

        // Assert: token is generated and username can be extracted
        assertNotNull(token);
        String extractedUsername = jwtService.extractUserName(token);
        assertEquals(username, extractedUsername);
    }

    @Test
    void isValidToken_InvalidToken_ReturnsFalse() {
        // Arrange: an invalid token string
        String invalidToken = "invalid.token.string";

        // Act
        boolean isValid = jwtService.isValidToken(invalidToken);

        // Assert
        assertFalse(isValid);
    }
}
