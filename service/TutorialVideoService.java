package com.pickellbal.service;

import com.pickellbal.model.TutorialVideo;
import com.pickellbal.repository.TutorialVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialVideoService {

    @Autowired
    private TutorialVideoRepository tutorialVideoRepository;

    public List<TutorialVideo> findAll() {
        return tutorialVideoRepository.findAll();
    }

    public TutorialVideo save(TutorialVideo tutorialVideo) {
        return tutorialVideoRepository.save(tutorialVideo);
    }
    public Optional<TutorialVideo> findById(int id) {
        return tutorialVideoRepository.findById(id);
    }
} 