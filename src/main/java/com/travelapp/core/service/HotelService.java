package com.travelapp.core.service;

import com.travelapp.core.model.Hotel;
import com.travelapp.core.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Hotel payload) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(payload.getId());
        if (!hotelOptional.isPresent()) {
            throw new IllegalStateException("Hotel does not exist");
        }
        hotelRepository.deleteById(payload.getId());
    }

    public Hotel updateHotel(String hotelId, Hotel payload) throws Exception {
        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        if(hotel.isEmpty())
            throw new Exception("Cannot find hotel with provided payload");

        hotel.get().setName(payload.getName());
        hotel.get().setLocation(payload.getLocation());
        hotel.get().setDescription(payload.getDescription());
        hotel.get().setPricePerNight(payload.getPricePerNight());
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

    public List<Hotel> filter(Double price, String location) {
        return hotelRepository.findHotelByPricePerNightGreaterThanAndLocation(price, location);
    }
}
