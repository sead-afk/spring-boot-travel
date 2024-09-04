package com.travelapp.rest.controllers;

import com.travelapp.core.model.Flight;
import com.travelapp.core.model.Hotel;
import com.travelapp.core.service.HotelService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/hotel")
@SecurityRequirement(name = "JWT Security")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/my-hotels")
    @PreAuthorize("hasRole('MEMBER')")
    public List<Hotel> getCurrentUserHotels() {    //my Booked hotels
        return hotelService.getCurrentUserHotels();
    }

    @PostMapping(path = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Hotel addHotel(@RequestBody Hotel hotel){
        return hotelService.addHotel(hotel);
    }

    @GetMapping(path = "/")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    public List<Hotel> getAll(){
        return hotelService.getHotels();
    }

    @PutMapping(path = "/{hotelId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Hotel updateHotel(@PathVariable("hotelId") String hotelId, @RequestBody Hotel hotelpayload) throws Exception {
        return hotelService.updateHotel(hotelId, hotelpayload);
    }

    @DeleteMapping(path = "/{hotel}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteHotel(@RequestBody Hotel hotel) {
        hotelService.deleteHotel(hotel);
    }
    @GetMapping(path = "/filter")
    @PreAuthorize("hasAnyAuthority('MEMBER', 'ADMIN')")
    public List<Hotel> filterHotel(
            @RequestParam("pricePerNight") double price,
            @RequestParam("location") String location
    ){
        return hotelService.filter(price, location);
    }

    @GetMapping(path = "/{hotelId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Hotel getHotelById(@PathVariable String hotelId) throws Exception {
        return hotelService.getHotelById(hotelId);
    }
}
