package com.lcwd.rating.Rating.Service.service.impl;

import com.lcwd.rating.Rating.Service.entities.Rating;
import com.lcwd.rating.Rating.Service.repository.RatingRepo;
import com.lcwd.rating.Rating.Service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingserviceImpl implements RatingService {
    @Autowired
    private RatingRepo ratingRepo;
    @Override
    public Rating create(Rating rating) {
        String rt=UUID.randomUUID().toString();
        rating.setRatingId(rt);
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getRaing() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepo.findByHotelId(hotelId);
    }
}
