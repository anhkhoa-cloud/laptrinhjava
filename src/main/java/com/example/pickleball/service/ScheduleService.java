package com.example.pickleball.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pickleball.model.Coach;
import com.example.pickleball.model.Schedule;
import com.example.pickleball.repository.ScheduleRepository;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

   public List<Schedule> getSchedulesByCoach(Coach coach) {
        return scheduleRepository.findByCoach(coach);
    }

    
}
