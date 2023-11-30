package com.travelapp.core.service;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.User;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {
    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public Destination addDestination(Destination destination) {
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
}
