package com.travelapp.core.repository;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.User;
import com.travelapp.core.model.enums.DestinationType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends MongoRepository<Destination, String> {
    public List<Destination> findAllByDestinationTypeOrNameLike(DestinationType destinationType, String name);
    List<Destination> findByUserId(User user);
    public List<Destination> findAllByDestinationTypeAndNameEquals(DestinationType destinationType, String name);

    public Destination findFirstByNameEquals(String name);
}
