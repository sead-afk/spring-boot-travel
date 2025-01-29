package com.travelapp.rest.controllers;


import com.travelapp.core.model.Room;
import com.travelapp.core.service.RoomService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/rooms")
@SecurityRequirement(name = "JWT Security")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /*@GetMapping("/my-rooms")
    @PreAuthorize("hasRole('MEMBER')")
    public List<Room> getCurrentUserRooms() {    //my Booked rooms
        return roomService.getCurrentUserRooms();
    }*/

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Room addRoom(@RequestBody Room room){
        //var username = SecurityContextHolder.getContext().getAuthentication();
        return roomService.addRoom(room);
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")     //mozda i guest
    public List<Room> getAll(){
        return roomService.getRooms();
    }

    @PutMapping(path = "/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Room updateRoom(@PathVariable("roomId") String roomId, @RequestBody Room roompayload) throws Exception {
        return roomService.updateRoom(roomId, roompayload);
    }

    @DeleteMapping(path = "/delete/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteRoom(@PathVariable("roomId") String roomId) {
        roomService.deleteRoom(roomId);
    }

    /*@GetMapping(path = "/filter")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    public List<Room> filterRoom(
            @RequestParam("pricePerNight") double price,
            @RequestParam("location") String location
    ){
        return roomService.filter(price, location);
    }*/

    @GetMapping(path = "/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Room getRoomById(@PathVariable String roomId) throws Exception {
        return roomService.getRoomById(roomId);
    }

    @GetMapping(path = "/{roomNumber}")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    public Room getRoomByNumber(@PathVariable int roomNumber) throws Exception {
        return roomService.getRoomByNumber(roomNumber);
    }
}
