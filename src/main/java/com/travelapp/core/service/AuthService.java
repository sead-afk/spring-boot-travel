package com.travelapp.core.service;


import com.travelapp.core.model.User;
import com.travelapp.core.repository.UserRepository;
import com.travelapp.rest.dto.LoginDTO;
import com.travelapp.rest.dto.LoginRequestDTO;
import com.travelapp.rest.dto.UserDTO;
import com.travelapp.rest.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
       userRequestDTO.setPassword(
               passwordEncoder.encode(userRequestDTO.getPassword())
       );
       User user = userRepository.save(userRequestDTO.toEntity());
       return new UserDTO(user);
    }

    public LoginDTO signIn(LoginRequestDTO loginRequestDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),
                        loginRequestDTO.getPassword())
        );
        User user = userRepository.findUserByEmail(loginRequestDTO.getEmail()).
                orElseThrow(() -> new IllegalArgumentException("This user does not exist"));
        String jwt = jwtService.generateToken(user);
        return new LoginDTO(jwt);
    }

}
