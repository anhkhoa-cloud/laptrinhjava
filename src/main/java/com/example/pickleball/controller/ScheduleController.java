package com.example.pickleball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pickleball.model.Coach;
import com.example.pickleball.model.User;
import com.example.pickleball.service.CoachService;
import com.example.pickleball.service.ScheduleService;
import com.example.pickleball.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/coach")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private UserService userService;
    @Autowired
    private CoachService coachService;
   
    @GetMapping("/schedule")
    public String viewSchedule(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        User user = userService.getUserById(userId);
        Coach coach = coachService.getCoachByUser(user);
        model.addAttribute("schedules", scheduleService.getSchedulesByCoach(coach));
        return "coach/schedule";
    }
}
