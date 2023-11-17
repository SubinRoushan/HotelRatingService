package com.lcwd.hotel.repository;

import com.lcwd.hotel.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel,String> {
}
