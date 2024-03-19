package com.travelapp.rest.controllers;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.User;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.service.DestinationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/destination")
public class DestinationController {
    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping(path = "/add")
    public Destination addDestination(@RequestBody Destination destination){
        return destinationService.addDestination(destination);
    }

    @GetMapping(path = "/")
    public List<Destination> getAll(){
        return destinationService.getAll();
    }

    @DeleteMapping(path = "/{destination}")
    public void deleteDestination(@RequestBody Destination destination) {
        destinationService.deleteDestination(destination);
    }
    @PutMapping(path = "/{destinationId}")
    public Destination updateDestination(@PathVariable("destinationId") String destinationId, @RequestBody Destination destinationPayload) throws Exception {
        return destinationService.updateDestination(destinationId, destinationPayload);
    }
    @GetMapping(path = "/filter")
    public List<Destination> filterDestinations(
            @RequestParam("type") DestinationType destinationType,
            @RequestParam("name") String name
    ){
        return destinationService.filter(destinationType, name);
    }
    @GetMapping(path = "/{destinationId}")
    public Destination getDestinationById(@PathVariable String destinationId) throws Exception {
        return destinationService.getDestinationById(destinationId);
    }
}
