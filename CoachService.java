package com.pickleball.khoa.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickleball.khoa.admin.model.Coach;
import com.pickleball.khoa.admin.repository.CoachRepository;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    public List<Coach> getAllPendingCoaches() {
        return coachRepository.findByIsApproved(false);
    }

    public boolean approveCoach(Long id) {
        Optional<Coach> coachOpt = coachRepository.findById(id);
        if (coachOpt.isPresent()) {
            Coach coach = coachOpt.get();
            coach.setApproved(true);
            coachRepository.save(coach);
            return true;
        }
        return false;
    }

    public long countAll() {
    return coachRepository.count();
    }

    public List<Coach> getAllCoaches() {
    return coachRepository.findAll();
    }

    public void deleteCoach(Long id) {
    coachRepository.deleteById(id);
    }

    public Coach createCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    public Coach updateCoach(Long id, Coach updatedCoach) {
        Optional<Coach> coachOpt = coachRepository.findById(id);
        if (coachOpt.isPresent()) {
            Coach coach = coachOpt.get();
            coach.setUserId(updatedCoach.getUserId());
            coach.setBio(updatedCoach.getBio());
            coach.setImagePath(updatedCoach.getImagePath());
            coach.setHourlyRate(updatedCoach.getHourlyRate());
            coach.setApproved(updatedCoach.getApproved());
            coach.setCreatedAt(updatedCoach.getCreatedAt());
            return coachRepository.save(coach);
        }
        return null;
    }
}
