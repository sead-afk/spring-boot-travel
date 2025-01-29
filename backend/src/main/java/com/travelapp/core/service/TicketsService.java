package com.travelapp.core.service;

import com.travelapp.core.model.Tickets;
import com.travelapp.core.repository.TicketsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TicketsService {

    private final TicketsRepository ticketsRepository;

    /*public List<Tickets> getCurrentUserTicketss() {  //my booked Ticketss
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        return ticketsRepository.findByUserid(user);
    }*/

    public List<Tickets> getTickets() {
        return ticketsRepository.findAll();
    }

    public Tickets addTickets(Tickets tickets) {
        /*var username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        //tickets.setUserId(user.getId());
        tickets.setUserid(user.getId());*/     //try and catch error?
        return ticketsRepository.save(tickets);
    }

    public void deleteTickets(String ticketsId) {
        Optional<Tickets> ticketsOptional = ticketsRepository.findById(ticketsId);
        if (!ticketsOptional.isPresent()) {
            throw new IllegalStateException("Tickets does not exist");
        }
        ticketsRepository.deleteById(ticketsId);
    }

    public Tickets updateTickets(String ticketsId, Tickets payload) throws Exception {
        Optional<Tickets> tickets = ticketsRepository.findById(ticketsId);
        if(tickets.isEmpty())
            throw new Exception("Cannot find tickets with provided payload");

        tickets.get().setSeatNumber(payload.getSeatNumber());
        tickets.get().setPrice(payload.getPrice());

        ticketsRepository.save(tickets.get());
        return tickets.get();
    }

    public Tickets getTicketsById(String ticketsId) throws Exception {
        Optional<Tickets> tickets = ticketsRepository.findById(ticketsId);
        if(tickets.isEmpty())
            throw new Exception("Cannot find tickets with provided payload");

        return tickets.get();
    }

}
