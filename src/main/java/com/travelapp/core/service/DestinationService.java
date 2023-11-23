package com.travelapp.core.service;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {
    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public Destination addDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    public List<Destination> getAll() {
        return destinationRepository.findAll();
    }

    public List<Destination> filter(DestinationType destinationType, String name) {
        return destinationRepository.findAllByDestinationTypeOrNameLike(destinationType, name);
    }
}
