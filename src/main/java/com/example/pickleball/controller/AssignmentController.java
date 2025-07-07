package com.example.pickleball.controller;

import com.example.pickleball.model.Assignment;
import com.example.pickleball.model.User;
import com.example.pickleball.repository.AssignmentRepository;
import com.example.pickleball.repository.UserRepository;
import com.example.pickleball.service.AssignmentService;
import com.example.pickleball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/coach/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    // Hiển thị danh sách học viên để giao bài tập
    @GetMapping
    public String listStudents(Model model) {
        List<User> students = userRepository.findByRole(User.Role.STUDENT);
        model.addAttribute("students", students);
        return "coach/assignment";
    }

    // Hiển thị form giao bài tập cho 1 học viên
    @GetMapping("/add/{studentId}")
    public String showAddForm(@PathVariable("studentId") Integer studentId, Model model) {
        User student = userRepository.findById(studentId).orElse(null);
        if (student == null) {
            return "redirect:/coach/assignment?error=studentnotfound";
        }
        model.addAttribute("student", student);
        model.addAttribute("assignment", new Assignment());
        // Lấy danh sách bài tập đã giao cho học viên này (nếu muốn hiển thị tiến độ)
        List<Assignment> assignments = assignmentRepository.findByStudent(student);
        model.addAttribute("studentAssignments", assignments);
        return "coach/add-assignment";
    }

    // Xử lý giao bài tập
   @PostMapping("/add/{studentId}")
public String addAssignment(@PathVariable("studentId") Integer studentId,
                            @ModelAttribute("assignment") Assignment assignment,
                            @RequestParam(value = "attachment", required = false) MultipartFile attachment,
                            HttpSession session) {
    User student = userRepository.findByUserId(studentId).orElse(null);
    if (student == null) {
        return "redirect:/coach/assignment?error=studentnotfound";
    }
    Integer coachId = (Integer) session.getAttribute("userId");
    User coach = userRepository.findByUserId(coachId).orElse(null);
    assignment.setStudent(student);
    assignment.setCoach(coach);
    assignment.setAssignedDate(LocalDate.now());
    assignment.setCompleted(false);

    // Xử lý file đính kèm
    if (attachment != null && !attachment.isEmpty()) {
        try {
            String fileName = attachment.getOriginalFilename();
            java.nio.file.Path uploadPath = java.nio.file.Paths.get("uploads");
            if (!java.nio.file.Files.exists(uploadPath)) {
                java.nio.file.Files.createDirectories(uploadPath);
            }
            java.nio.file.Path filePath = uploadPath.resolve(fileName);
            attachment.transferTo(filePath.toFile());
            // assignment.setAttachmentPath(filePath.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    assignmentRepository.save(assignment);
    return "redirect:/coach/assignment/add/" + studentId + "?success=true";
}
}
