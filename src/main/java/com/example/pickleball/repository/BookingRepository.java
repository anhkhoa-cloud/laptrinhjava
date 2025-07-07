package com.example.pickleball.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pickleball.model.Booking;
import com.example.pickleball.model.Booking.Status;
import com.example.pickleball.model.Coach;
import com.example.pickleball.model.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
     List<Booking> findByCoach(Coach coach);
      List<Booking> findAllByOrderByCreatedAtDesc();
      List<Booking> findByCoachAndStatus(Coach coach, Status status);
      // Thêm vào interface BookingRepository
      List<Booking> findByStudentAndCoach(User student, Coach coach);
    

    
}
