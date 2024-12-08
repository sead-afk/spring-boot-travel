package com.travelapp.core.service;

import com.travelapp.core.model.Flight;
import com.travelapp.core.model.Hotel;
import com.travelapp.core.model.User;
import com.travelapp.core.repository.HotelRepository;
import com.travelapp.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private UserRepository userRepository;

    public HotelService(HotelRepository hotelRepository, UserRepository userRepository) {
        this.hotelRepository = hotelRepository;
        this.userRepository = userRepository;
    }

    /*public List<Hotel> getCurrentUserHotels() {  //my booked Hotels
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        return hotelRepository.findByUserid(user);
    }*/

    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    public Hotel addHotel(Hotel hotel) {
        var username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username);
        //hotel.setUserid(user.getId());
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(String hotelId) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
        if (!hotelOptional.isPresent()) {
            throw new IllegalStateException("Hotel does not exist");
        }
        hotelRepository.deleteById(hotelId);
    }

    public Hotel updateHotel(String hotelId, Hotel payload) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(hotel.isEmpty())
            throw new Exception("Cannot find hotel with provided payload");

        hotel.get().setName(payload.getName());
        hotel.get().setLocation(payload.getLocation());
        hotel.get().setDescription(payload.getDescription());
        hotel.get().setAmenities(payload.getAmenities());

        hotelRepository.save(hotel.get());
        return hotel.get();
    }

    public Hotel getHotelById(String hotelId) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(hotel.isEmpty())
            throw new Exception("Cannot find hotel with provided payload");

        return hotel.get();
    }

    /*public List<Hotel> filter(Double price, String location) {
        return hotelRepository.findHotelByPricePerNightGreaterThanAndLocation(price, location);
    }*/
}
