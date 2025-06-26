package com.example.pickleball.controller.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.pickleball.model.Coach;
import com.example.pickleball.service.CoachService;

@Controller
@RequestMapping("/coach")
public class CoachViewController {

    @Autowired
    private CoachService coachService;

    // Trang xem danh sách các booking của huấn luyện viên
    @GetMapping("/bookings")
    public String viewBookings() {
        return "coach/bookings";
    }

    // Xem lịch dạy 
    @GetMapping("/booking-calendar")
    public String createBooking() {
        return "coach/booking-calendar";
    }

    // Xem profile của huấn luyện viên
 @GetMapping("/profile")
public String viewProfile(Model model) {
    Coach coach = coachService.getCoachById(1L);
    System.out.println("Coach: " + coach);
    model.addAttribute("coach", coach);
    return "coach/profile";
}

    // Sửa thông tin cá nhân
    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        Coach coach = coachService.getCoachById(1L);
        model.addAttribute("coach", coach);
        return "coach/profile-edit";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute Coach coach) {
        coachService.saveCoach(coach);
        return "redirect:/coach/profile";
    }
}