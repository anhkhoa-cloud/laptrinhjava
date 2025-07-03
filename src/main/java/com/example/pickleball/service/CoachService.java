package com.example.pickleball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pickleball.model.Coach;
import com.example.pickleball.model.User;
import com.example.pickleball.repository.CoachRepository;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;
    
    //Lấy coach theo user
    public Coach getCoachByUser(User user){
        return coachRepository.findByUser(user).orElse(null);
    }
    // Lấy coach theo id
    public Coach getCoachById(Long id) {
        return coachRepository.findById(id).orElse(null);
    }

    // Cập nhật thông tin coach
    public Coach updateCoachProfile(Coach coach) {
        return coachRepository.save(coach);
    }
}
