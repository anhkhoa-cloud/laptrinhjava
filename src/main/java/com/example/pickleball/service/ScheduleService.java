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

      @Autowired
    private CoachService coachService;

   public List<Schedule> getSchedulesByCoach(Coach coach) {
        return scheduleRepository.findByCoach(coach);
    }


     public int countSchedulesForCoach(Integer coachUserId) {
      Coach coach = coachService.getCoachByUserId(coachUserId);
      if (coach == null) return 0;
      return scheduleRepository.findByCoach(coach).size();
  }

    // Lấy danh sách lịch dạy của coach (nếu cần)
    public List<Schedule> getSchedulesForCoach(Integer coachUserId) {
        Coach coach = coachService.getCoachByUserId(coachUserId);
        if (coach == null) return List.of();
        return scheduleRepository.findByCoach(coach);
    }
}


    
