package com.travelapp.core.service;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.User;
import com.travelapp.core.repository.UserRepository;
import com.travelapp.rest.dto.UserDTO;
import com.travelapp.rest.dto.UserRequestDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        // List<User> users = userRepository.findAllCustom();

        return users
                .stream()
                .map(UserDTO::new)
                .collect(toList());
    }

    public UserDTO addNewUser(UserRequestDTO payload) {
        Optional<User> userOptional = userRepository.findUserByEmail(payload.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        User user = userRepository.save(payload.toEntity());
        return new UserDTO(user);
    }

    public void deleteUser(String userId) {
        Optional<User> userOptional = userRepository.findUserById(userId);
        if (!userOptional.isPresent()) {
            throw new IllegalStateException("User does not exist");
        }
        userRepository.deleteById(userId);
    }

    public UserDTO updateUser(String userId, UserRequestDTO  payload) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new Exception("Cannot find user with provided payload");

        User updatedUser = payload.toEntity();
        updatedUser.setId(user.get().getId());
        updatedUser = userRepository.save(updatedUser);
        return new UserDTO(updatedUser);
    }

    public UserDTO getUserById(String userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new Exception("Cannot find user with provided payload");

        return new UserDTO(user.get());
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {

                User user = userRepository.findByUsernameOrEmail(username).orElse(null);
                //return userRepository.findByUsernameOrEmail(username)
                        //.orElseThrow(() -> new UsernameNotFoundException("User not found."));
                return user;
            }
        };
    }

    /*public User getCurrentUserAccountDetails() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        return user;
    }*/

    public User getUserProfile(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Element not found in the collection."));
    }

    public void addFundsToUser(String username, double amount) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
    }
}
