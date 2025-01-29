package com.travelapp.rest.controllers;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.User;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.service.DestinationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/destinations")
@SecurityRequirement(name = "JWT Security")
public class DestinationController {
    /*private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/my-destinations")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Destination> getCurrentUserDestinations() {
        return destinationService.getCurrentUserDestinations();
    }

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Destination addDestination(@RequestBody Destination destination){
        var username = SecurityContextHolder.getContext().getAuthentication();
        return destinationService.addDestination(destination);
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','GUEST')")
    public List<Destination> getAll(){
        return destinationService.getAll();
    }

    @DeleteMapping(path = "/delete/{destinationId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteDestination(@PathVariable("destinationId") String destinationId) {
        destinationService.deleteDestination(destinationId);
    }
    @PutMapping(path = "/update/{destinationId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Destination updateDestination(@PathVariable("destinationId") String destinationId, @RequestBody Destination destinationPayload) throws Exception {
        return destinationService.updateDestination(destinationId, destinationPayload);
    }
    @GetMapping(path = "/filter")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Destination> filterDestinations(
            @RequestParam("type") DestinationType destinationType,
            @RequestParam("name") String name
    ){
        return destinationService.filter(destinationType, name);
    }
    @GetMapping(path = "/{destinationId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Destination getDestinationById(@PathVariable String destinationId) throws Exception {
        return destinationService.getDestinationById(destinationId);
    }*/
}
