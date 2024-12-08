package com.travelapp.rest.controllers;

import com.travelapp.core.service.AuthService;
import com.travelapp.rest.dto.LoginDTO;
import com.travelapp.rest.dto.LoginRequestDTO;
import com.travelapp.rest.dto.UserDTO;
import com.travelapp.rest.dto.UserRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
@SecurityRequirement(name = "JWT Security")
@PreAuthorize("permitAll()")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserRequestDTO user){
        return ResponseEntity.ok(authService.signUp(user));
    }
    @PreAuthorize("permitAll()")
    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            var result=authService.signIn(loginRequestDTO);
            return ResponseEntity.ok(result);
        } catch (AuthenticationException e)
        {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }

    }
}
