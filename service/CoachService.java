package com.pickellbal.service;

import com.pickellbal.model.Coach;
import com.pickellbal.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {
    @Autowired
    private CoachRepository coachRepository;

    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    public Optional<Coach> findById(int id) {
        return coachRepository.findById(id);
    }

    public Coach save(Coach coach) {
        return coachRepository.save(coach);
    }

    public void deleteById(int id) {
        coachRepository.deleteById(id);
    }
} 