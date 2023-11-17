package com.lcwd.rating.Rating.Service.service;

import com.lcwd.rating.Rating.Service.entities.Rating;

import java.util.List;

public interface RatingService {
    //create
    Rating create(Rating rating);
    //Get all rating
    List<Rating> getRaing();
    //get all by UserId
    List<Rating> getRatingByUserId(String userId);
    //get all by Hostel
    List<Rating> getRatingByHotelId(String hotelId);
}
