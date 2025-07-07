package com.example.pickleball.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pickleball.model.Booking;
import com.example.pickleball.model.Coach;
import com.example.pickleball.model.User;
import com.example.pickleball.service.BookingService;
import com.example.pickleball.service.CoachService;
import com.example.pickleball.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/coach/student")
public class StudentController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private CoachService coachService;

    // Xem danh sách học viên
    @GetMapping
    public String listStudents(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        List<User> students = userService.getAllUsersByRole(User.Role.STUDENT);
        model.addAttribute("students", students);
        return "coach/student";
    }

    // Xoá học viên
    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable("id") int id, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        userService.deleteUser(id);
        return "redirect:/coach/student";
    }

    // Xem chi tiết học viên
    @GetMapping("/{id}")
    public String viewStudent(@PathVariable("id") int id, HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        User student = userService.getUserById(id);
        if (student == null || student.getRole() != User.Role.STUDENT) {
            return "redirect:/coach/student";
        }

        // Lấy HLV hiện tại
        User currentCoachUser = userService.getUserById(userId);
        Coach coach = coachService.getCoachByUser(currentCoachUser);

        // Lấy danh sách buổi học của học viên này với HLV này
        List<Booking> studentBookings = bookingService.getBookingsByStudentAndCoach(student, coach);

        // Tính tiến độ tổng thể
        int totalSessions = studentBookings.size();
        int completedSessions = (int) studentBookings.stream()
            .filter(booking -> booking.getStatus() == Booking.Status.HOÀN_THÀNH)
            .count();
        int progressPercentage = totalSessions > 0 ? (completedSessions * 100) / totalSessions : 0;

        model.addAttribute("student", student);
        model.addAttribute("bookings", studentBookings);
        model.addAttribute("totalSessions", totalSessions);
        model.addAttribute("completedSessions", completedSessions);
        model.addAttribute("progressPercentage", progressPercentage);

        return "coach/student-detail";
    }
}
