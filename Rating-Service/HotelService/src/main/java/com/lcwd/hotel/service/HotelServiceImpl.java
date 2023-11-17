package com.lcwd.hotel.service;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.repository.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;
@Service
public class HotelServiceImpl implements HotelService{
    @Autowired
    private HotelRepo hotelRepo;
    @Override
    public Hotel create(Hotel hotel) {
        String genId=UUID.randomUUID().toString();
        hotel.setId(genId);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getall() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getSingle(String id) {
        return hotelRepo.findById(id).orElseThrow(()->new ResolutionException("User is Not found"+id));
    }
}
