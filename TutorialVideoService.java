package com.pickleball.khoa.admin.service;

import com.pickleball.khoa.admin.model.TutorialVideo;
import com.pickleball.khoa.admin.repository.TutorialVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialVideoService {

    @Autowired
    private TutorialVideoRepository tutorialVideoRepository;

    public List<TutorialVideo> getAllVideos() {
        return tutorialVideoRepository.findAll();
    }

    public Optional<TutorialVideo> getVideoById(Long id) {
        return tutorialVideoRepository.findById(id);
    }

    public TutorialVideo saveVideo(TutorialVideo video) {
        return tutorialVideoRepository.save(video);
    }

    public void deleteVideo(Long id) {
        tutorialVideoRepository.deleteById(id);
    }

    public long countAll() {
        return tutorialVideoRepository.count();
    }
}
