package com.lcwd.hotel.service;

import com.lcwd.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    //Create
    Hotel create(Hotel hotel);
    //Get All
    List<Hotel> getall();
    //get single
    Hotel getSingle(String id);
}
