package com.travelapp.core.service;

import com.travelapp.core.model.User;
import com.travelapp.core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);
    }

    public void deleteUser(User payload) {
        Optional<User> userOptional = userRepository.findUserByEmail(payload.getEmail());
        if (!userOptional.isPresent()) {
            throw new IllegalStateException("User does not exist");
        }
        userRepository.deleteById(payload.getId());
    }

    public User updateUser(String userId, User payload) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new Exception("Cannot find user with provided payload");

        user.get().setUsername(payload.getUsername());
        user.get().setEmail(payload.getEmail());

        userRepository.save(user.get());
        return user.get();
    }

    public User getUserById(String userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new Exception("Cannot find user with provided payload");

        return user.get();
    }
}
