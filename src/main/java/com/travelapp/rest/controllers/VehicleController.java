package com.travelapp.rest.controllers;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.Vehicle;
import com.travelapp.core.model.enums.DestinationType;
import com.travelapp.core.model.enums.VehicleType;
import com.travelapp.core.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping(path = "/add")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.addVehicle(vehicle);
    }

    @GetMapping(path = "/")
    public List<Vehicle> getAll(){
        return vehicleService.getAll();
    }

    @GetMapping(path = "/filter")
    public List<Vehicle> filterVehicle(
            @RequestParam("type") VehicleType vehicleType,
            @RequestParam("name") String name
    ){
        return vehicleService.filter(vehicleType, name);
    }
}
