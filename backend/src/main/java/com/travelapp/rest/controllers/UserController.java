package com.travelapp.rest.controllers;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.User;
import com.travelapp.core.service.UserService;
import com.travelapp.rest.dto.UserDTO;
import com.travelapp.rest.dto.UserRequestDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
@SecurityRequirement(name = "JWT Security")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/my-account")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public User getCurrentUserAccountDetails() {
        return userService.getCurrentUserAccountDetails();
    }

    @GetMapping(path = "/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDTO getUserById(@PathVariable String userId) throws Exception {
        return userService.getUserById(userId);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('GUEST','ADMIN')")
    public UserDTO registerNewUser(@RequestBody UserRequestDTO user) {
        return userService.addNewUser(user);
    }

    @DeleteMapping(path = "/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MEMBER')")
    public UserDTO updateUser(@PathVariable("userId") String userId, @RequestBody UserRequestDTO userPayload) throws Exception {
        return userService.updateUser(userId, userPayload);
    }
}
