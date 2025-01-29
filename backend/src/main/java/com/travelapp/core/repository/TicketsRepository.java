package com.travelapp.core.repository;

import com.travelapp.core.model.Hotel;
import com.travelapp.core.model.Tickets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsRepository extends MongoRepository<Tickets, String> {
    public List<Tickets> findAllBySeatNumber(String seatNumber);
}
