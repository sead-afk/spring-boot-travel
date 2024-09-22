package com.travelapp.core.service;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.User;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.repository.DestinationRepository;
import com.travelapp.core.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {
    private final DestinationRepository destinationRepository;
    private UserRepository userRepository;

    public DestinationService(DestinationRepository destinationRepository, UserRepository userRepository) {
        this.destinationRepository = destinationRepository;
        this.userRepository = userRepository;
    }

    public List<Destination> getCurrentUserDestinations() {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        return destinationRepository.findByUserId(user);
    }

    public Destination addDestination(Destination destination) {
        var username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        destination.setUserId(user.getId());
        return destinationRepository.save(destination);
    }

    public Destination updateDestination(String destinationId, Destination payload) throws Exception {
        Optional<Destination> destination = destinationRepository.findById(destinationId);
        if(destination.isEmpty())
            throw new Exception("Cannot find user with provided payload");

        destination.get().setName(payload.getName());
        destination.get().setDestinationType(payload.getDestinationType());

        destinationRepository.save(destination.get());
        return destination.get();
    }

    public List<Destination> getAll() {
        return destinationRepository.findAll();
    }

    public List<Destination> filter(DestinationType destinationType, String name) {
        return destinationRepository.findAllByDestinationTypeOrNameLike(destinationType, name);
    }

    public void deleteDestination(String destinationId) {
        Optional<Destination> destinationOptional = destinationRepository.findById(destinationId);
        if (!destinationOptional.isPresent()) {
            throw new IllegalStateException("Destination does not exist");
        }
        destinationRepository.deleteById(destinationId);
    }

    public Destination getDestinationById(String destinationId) throws Exception {
        Optional<Destination> destination = destinationRepository.findById(destinationId);
        if(destination.isEmpty())
            throw new Exception("Cannot find destination with provided payload");

        return destination.get();
    }
}
