package com.example.pickleball.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pickleball.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByStudentId(Long studentId);
    List<Booking> findByCoachId(Long coachId);
   

}
