package com.lcwd.hotel.controller;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    //Create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }
    //Get All
    @GetMapping
    public ResponseEntity<List<Hotel>> getall()
    {
        List<Hotel> hotel=hotelService.getall();
        return ResponseEntity.ok(hotel);
    }
    //Get single
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getSingle(@PathVariable String id){
       Hotel hotel1=hotelService.getSingle(id);
       return ResponseEntity.ok(hotel1);
    }
}
