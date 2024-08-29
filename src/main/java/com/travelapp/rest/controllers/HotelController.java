package com.travelapp.rest.controllers;

import com.travelapp.core.model.Hotel;
import com.travelapp.core.service.HotelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/hotel")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(path = "/add")
    public Hotel addHotel(@RequestBody Hotel hotel){
        return hotelService.addHotel(hotel);
    }

    @GetMapping(path = "/")
    public List<Hotel> getAll(){
        return hotelService.getHotels();
    }

    @PutMapping(path = "/{hotelId}")
    public Hotel updateHotel(@PathVariable("hotelId") String hotelId, @RequestBody Hotel hotelpayload) throws Exception {
        return hotelService.updateHotel(hotelId, hotelpayload);
    }

    @DeleteMapping(path = "/{hotel}")
    public void deleteHotel(@RequestBody Hotel hotel) {
        hotelService.deleteHotel(hotel);
    }
    @GetMapping(path = "/filter")
    public List<Hotel> filterHotel(
            @RequestParam("pricePerNight") double price,
            @RequestParam("location") String location
    ){
        return hotelService.filter(price, location);
    }

    @GetMapping(path = "/{hotelId}")
    public Hotel getHotelById(@PathVariable String hotelId) throws Exception {
        return hotelService.getHotelById(hotelId);
    }
}
