package com.example.pickleball.service;

import com.example.pickleball.model.Assignment;
import com.example.pickleball.model.User;
import com.example.pickleball.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    // Lấy danh sách bài tập của 1 học viên
    public List<Assignment> getAssignmentsByStudent(User student) {
        return assignmentRepository.findByStudent(student);
    }

    // Giao bài tập mới
    public Assignment createAssignment(Assignment assignment) {
        // assignedDate và completed nên set ở controller trước khi gọi service
        return assignmentRepository.save(assignment);
    }
}
    

