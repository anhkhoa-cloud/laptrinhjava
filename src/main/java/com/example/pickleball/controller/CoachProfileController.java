package com.example.pickleball.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.pickleball.model.Coach;
import com.example.pickleball.model.User;
import com.example.pickleball.service.CoachService;
import com.example.pickleball.service.ScheduleService;
import com.example.pickleball.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping("/coach")
public class CoachProfileController {
    @Autowired
    private CoachService coachService;

    @Autowired
private UserService userService;

    @Autowired
    private ScheduleService scheduleService;


   @GetMapping("/dashboard")
public String dashboard(Model model, HttpSession session) {
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        return "redirect:/login";
    }
    // Lấy user
    User user = userService.getUserById(userId);
    // Lấy coach từ user
    Coach coach = coachService.getCoachByUser(user);
    if (coach == null) {
        return "redirect:/login";
    }
      model.addAttribute("user", user);
        model.addAttribute("coach", coach);


    // Đếm tổng số học viên
    int studentCount = userService.countAllStudents();

    // Đếm lịch dạy của coach
    int scheduleCount = scheduleService.countSchedulesForCoach(coach.getCoachId().intValue());

    int reminderCount = 0;

    model.addAttribute("reminderCount", reminderCount);
    model.addAttribute("scheduleCount", scheduleCount);
    model.addAttribute("studentCount", studentCount);

    return "coach/dashboard";
}
    //Xem trang thông tin cá nhân
    @GetMapping("/profile")
    public String viewProfile(HttpSession session, Model model){
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        Coach coach = coachService.getCoachByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("coach", coach);

        return "coach/profile";
    }
    //Hiển thị trang cập nhật thông tin
    @GetMapping("/update-profile")
    public String showUpdateProfile(HttpSession session, Model model){
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        Coach coach = coachService.getCoachByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("coach", coach);

        return "coach/update-profile";
    }
    
    
    //Cập nhật profile
    @PostMapping("/update-profile")
public String updateProfile(
        @ModelAttribute User userForm,
        @ModelAttribute Coach coachForm,
        @RequestParam(value="avatarFile", required=false) MultipartFile avatarFile,
        HttpSession session,
        Model model) {

    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        model.addAttribute("errorMessage", "Bạn chưa đăng nhập!");
        return "coach/update-profile";
    }

    try {
        coachService.updateCoachProfile(userId, userForm, coachForm, avatarFile);
        model.addAttribute("successMessage", "Cập nhật thành công");
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("errorMessage", "Cập nhật không thành công! " + e.getMessage());
    }

    // Lấy lại thông tin mới để hiển thị
    User user = userService.getUserById(userId);
    Coach coach = coachService.getCoachByUser(user);
    model.addAttribute("user", user);
    model.addAttribute("coach", coach);

    return "coach/update-profile";
}
}

