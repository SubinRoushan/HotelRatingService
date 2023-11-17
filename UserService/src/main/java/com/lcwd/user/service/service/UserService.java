package com.lcwd.user.service.service;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {
    //Create
    User saveUser(User user);
    //get all users
    List<User> getAllUsers();
    //get single users
    User getUser(String userId);
}
