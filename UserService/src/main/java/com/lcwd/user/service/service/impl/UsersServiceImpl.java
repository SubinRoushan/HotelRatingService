package com.lcwd.user.service.service.impl;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.HotelService;
import com.lcwd.user.service.repository.UserRepository;
import com.lcwd.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId=UUID.randomUUID().toString(); //Generate Unique User Id
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        //get user from database with the help of repository
        User uSer=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !"+userId));
        //fetching rating of the above user from RATING SERVICE
        //http://localhost:8083/ratings/user/2c950280-477b-4ebd-a204-21d78adf3e2c
       Rating[] getobj= restTemplate.getForObject("http://RATING-SERVER/ratings/user/"+uSer.getUserId(),Rating[].class);
        List<Rating> ratings=Arrays.stream(getobj).toList();
       logger.info("{} ",getobj);

       List<Rating> listRating=ratings.stream().map(rating -> {
           //api call to hotel service to get the hotel
         //ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTEL-SERVER/hotels/"+rating.getHotelId(), Hotel.class);
         //  Hotel hotel=forEntity.getBody();  //Calling as Rest-Api

           Hotel hotel=hotelService.getHotel(rating.getHotelId()); //Calling as Feign Client

           //logger.info("Response Status code ",forEntity.getStatusCode());
           //set the hotel to rating
           rating.setHotel(hotel);
           //return rating
           return  rating;
       }).collect(Collectors.toList());

       uSer.setRatings(listRating); //set list of rating to user
       return uSer;
    }
}
