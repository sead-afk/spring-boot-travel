package com.travelapp.rest.controllers;

import com.travelapp.core.model.Tickets;
import com.travelapp.core.service.TicketsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/tickets")
@SecurityRequirement(name = "JWT Security")
public class TicketsController {

    private final TicketsService ticketsService;

    public TicketsController(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    /*@GetMapping("/my-ticketss")
    @PreAuthorize("hasRole('MEMBER')")
    public List<Tickets> getCurrentUserTicketss() {    //my Booked ticketss
        return ticketsService.getCurrentUserTicketss();
    }*/

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Tickets addTickets(@RequestBody Tickets tickets){
        //var username = SecurityContextHolder.getContext().getAuthentication();
        return ticketsService.addTickets(tickets);
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")     //mozda i guest
    public List<Tickets> getAll(){
        return ticketsService.getTickets();
    }

    @PutMapping(path = "/{ticketsId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Tickets updateTickets(@PathVariable("ticketsId") String ticketsId, @RequestBody Tickets ticketspayload) throws Exception {
        return ticketsService.updateTickets(ticketsId, ticketspayload);
    }

    @DeleteMapping(path = "/delete/{ticketsId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteTickets(@PathVariable("ticketsId") String ticketsId) {
        ticketsService.deleteTickets(ticketsId);
    }

    /*@GetMapping(path = "/filter")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    public List<Tickets> filterTickets(
            @RequestParam("pricePerNight") double price,
            @RequestParam("location") String location
    ){
        return ticketsService.filter(price, location);
    }*/

    @GetMapping(path = "/{ticketsId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Tickets getTicketsById(@PathVariable String ticketsId) throws Exception {
        return ticketsService.getTicketsById(ticketsId);
    }
    
}
