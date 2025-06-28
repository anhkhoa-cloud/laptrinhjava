package com.example.pickleball.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pickleball.model.Coach;

public interface CoachRepository extends JpaRepository<Coach, Long> {

    // Additional query methods can be defined here if needed

}
