package com.travelapp.rest.controllers;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.User;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.service.DestinationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/destinations")
@SecurityRequirement(name = "JWT Security")
public class DestinationController {
    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/my-destinations")
    @PreAuthorize("hasRole('MEMBER')")
    public List<Destination> getCurrentUserDestinations() {
        return destinationService.getCurrentUserDestinations();
    }

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Destination addDestination(@RequestBody Destination destination){
        return destinationService.addDestination(destination);
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Destination> getAll(){
        return destinationService.getAll();
    }

    @DeleteMapping(path = "/{destination}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteDestination(@RequestBody Destination destination) {
        destinationService.deleteDestination(destination);
    }
    @PutMapping(path = "/{destinationId}")
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
    }
}
