package com.lcwd.user.service.repository;

import com.lcwd.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    @Override
    Optional<User> findById(String s);
}
