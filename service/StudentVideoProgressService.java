package com.pickellbal.service;

import com.pickellbal.model.StudentVideoProgress;
import com.pickellbal.model.TutorialVideo;
import com.pickellbal.repository.StudentVideoProgressRepository;
import com.pickellbal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentVideoProgressService {
    @Autowired
    private StudentVideoProgressRepository studentVideoProgressRepository;

    @Autowired TutorialVideoService tutorialVideoService;

    public List<StudentVideoProgress> findAll() {
        return studentVideoProgressRepository.findAll();
    }

    public Optional<StudentVideoProgress> findById(int id) {
        return studentVideoProgressRepository.findById(id);
    }

    public StudentVideoProgress save(StudentVideoProgress progress) {
        return studentVideoProgressRepository.save(progress);
    }

    public void deleteById(int id) {
        studentVideoProgressRepository.deleteById(id);
    }

    public List<StudentVideoProgress> findByStudent(User student) {
        return studentVideoProgressRepository.findByStudent(student);
    }
    
    public void markAsCompleted(User student, int videoId) {
        
        Optional<TutorialVideo> videoOpt = tutorialVideoService.findById(videoId);
        if (!videoOpt.isPresent()) return;
        TutorialVideo video = videoOpt.get();
    
        // Tìm tiến độ
        Optional<StudentVideoProgress> optional = studentVideoProgressRepository.findByStudentAndVideo(student, video);
        StudentVideoProgress progress;
        if (optional.isPresent()) {
            return;
        } else {
            progress = new StudentVideoProgress();
            progress.setStudent(student);
            progress.setVideo(video);
        }
        progress.setCompleted(true);
        studentVideoProgressRepository.save(progress);
    }
} 