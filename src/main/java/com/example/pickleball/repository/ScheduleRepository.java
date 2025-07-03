package com.example.pickleball.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pickleball.model.Coach;
import com.example.pickleball.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
     List<Schedule> findByCoach(Coach coach);
    
}
