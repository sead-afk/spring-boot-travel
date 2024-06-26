package com.travelapp.rest.controllers;

import com.travelapp.core.model.Destination;
import com.travelapp.core.model.User;
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

    @PutMapping(path = "/{vehicleId}")
    public Vehicle updateVehicle(@PathVariable("vehicleId") String vehicleId, @RequestBody Vehicle vehiclepayload) throws Exception {
        return vehicleService.updateVehicle(vehicleId, vehiclepayload);
    }

    @DeleteMapping(path = "/{vehicle}")
    public void deleteVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.deleteVehicle(vehicle);
    }
    @GetMapping(path = "/filter")
    public List<Vehicle> filterVehicle(
            @RequestParam("type") VehicleType vehicleType,
            @RequestParam("name") String name
    ){
        return vehicleService.filter(vehicleType, name);
    }

    @GetMapping(path = "/{vehicleId}")
    public Vehicle getVehicleById(@PathVariable String vehicleId) throws Exception {
        return vehicleService.getVehicleById(vehicleId);
    }
}
