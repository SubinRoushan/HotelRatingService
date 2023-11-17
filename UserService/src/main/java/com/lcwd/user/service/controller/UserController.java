package com.lcwd.user.service.controller;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.repository.UserRepository;
import com.lcwd.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    private Logger logger= LoggerFactory.getLogger(UserController.class);
    //Create
    @PostMapping
    public ResponseEntity<User> createuser(@RequestBody User user)
    {
        User user1=userService.saveUser(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    //Get single users
    int retryCount=1;
    @GetMapping("/{Userid}")
   // @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    @Retry(name="ratingHotelService" ,fallbackMethod ="ratingHotelFallback" )
    public ResponseEntity<User> getuser(@PathVariable String Userid){
        logger.info("Retry count {} ",retryCount);
        retryCount++;
        User user=userService.getUser(Userid);
        return ResponseEntity.ok(user);
    }
    //crerating fall back method for circuitbreaker if service is down
    public ResponseEntity<User> ratingHotelFallback(String userID,Exception ex)
    {
        logger.info("FAllback is executed because server is down :",ex.getMessage());
        User user=User.builder()
                .name("Dummy")
                .email("dummy@gmail.com")
                .about("This user is created dummy because some service is dowm")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }


    //get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUser()
    {
        List<User> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
