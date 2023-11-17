package com.lcwd.rating.Rating.Service.repository;

import com.lcwd.rating.Rating.Service.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepo extends JpaRepository<Rating,String> {
    List<Rating> findByUserId(String userID);
    List<Rating> findByHotelId(String hotelId);
}
