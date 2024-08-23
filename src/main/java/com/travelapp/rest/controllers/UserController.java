package com.travelapp.rest.controllers;

import com.travelapp.core.model.User;
import com.travelapp.core.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{userId}")
    public User getUserById(@PathVariable String userId) throws Exception {
        return userService.getUserById(userId);
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "/{user}")
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }

    @PutMapping(path = "/{userId}")
    public User updateUser(@PathVariable("userId") String userId, @RequestBody User userPayload) throws Exception {
        return userService.updateUser(userId, userPayload);
    }
}
