package com.pickleball.khoa.admin.repository;

import com.pickleball.khoa.admin.model.TutorialVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorialVideoRepository extends JpaRepository<TutorialVideo, Long> {
    List<TutorialVideo> findByIsActive(boolean isActive);
    long count();
}
