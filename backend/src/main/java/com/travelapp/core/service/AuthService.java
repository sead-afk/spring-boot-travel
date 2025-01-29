package com.travelapp.core.service;


import com.travelapp.core.model.User;
import com.travelapp.core.repository.UserRepository;
import com.travelapp.rest.dto.LoginDTO;
import com.travelapp.rest.dto.LoginRequestDTO;
import com.travelapp.rest.dto.UserDTO;
import com.travelapp.rest.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO signUp(UserRequestDTO userRequestDTO) {
        if (userRepository.findUserByEmail(userRequestDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        if (userRepository.findByUsername(userRequestDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already in use.");
        }
       userRequestDTO.setPassword(
               passwordEncoder.encode(userRequestDTO.getPassword())
       );
       User user = userRepository.save(userRequestDTO.toEntity());
       return new UserDTO(user);
    }

    public LoginDTO signIn(LoginRequestDTO loginRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getEmail(),
                            loginRequestDTO.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        System.out.println("sejo");

        User user = userRepository.findUserByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist"));

        Map<String, String> claims = Map.of("email", user.getEmail());
        String jwt = jwtService.generateToken(claims, user);

        return new LoginDTO(jwt);
    }



}
