package com.example.pickleball.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pickleball.model.Coach;
import com.example.pickleball.model.User;

public interface CoachRepository extends JpaRepository<Coach, Long > {
   
    Optional<Coach> findByUser(User user);

    Coach findByUser_UserId(Integer coachUserId);
   
    

}
