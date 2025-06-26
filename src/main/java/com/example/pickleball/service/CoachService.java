package com.example.pickleball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pickleball.model.Coach;
import com.example.pickleball.repository.CoachRepository;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    public Coach getCoachById(Long id) {
        return coachRepository.findById(id).orElse(null);
    }

    public void saveCoach(Coach coach) {
        coachRepository.save(coach);
    }
}