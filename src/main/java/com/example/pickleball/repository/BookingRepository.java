package com.example.pickleball.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pickleball.model.Booking;
import com.example.pickleball.model.Coach;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
     List<Booking> findByCoach(Coach coach);
    

    
}
