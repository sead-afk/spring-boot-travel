package com.travelapp.rest.controllers;

import com.travelapp.core.model.User;
import com.travelapp.core.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
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

    @DeleteMapping(path = "/{userId}")
    public void deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "/{userId}")
    public User updateUser(@PathVariable("userId") String userId, @RequestBody User userPayload) throws Exception {
        return userService.updateUser(userId, userPayload);
    }
}
