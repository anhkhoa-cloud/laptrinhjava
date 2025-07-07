package com.example.pickleball.repository;

import com.example.pickleball.model.Assignment;
import com.example.pickleball.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

    List<Assignment> findByStudent(User student);
    List<Assignment> findByCoach(User coach);
}