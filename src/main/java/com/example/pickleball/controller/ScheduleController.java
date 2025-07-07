package com.example.pickleball.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.pickleball.model.Coach;
import com.example.pickleball.model.Schedule;
import com.example.pickleball.model.User;
import com.example.pickleball.service.CoachService;
import com.example.pickleball.service.ScheduleService;
import com.example.pickleball.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
     @Autowired
    private CoachService coachService;

    @Autowired
private UserService userService;


    // Trang giao diện lịch dạy (Thymeleaf)
    @GetMapping("/coach/schedule")
    public String viewSchedule(HttpSession session, Model model) {
       Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        Coach coach = coachService.getCoachByUser(user);
        
         session.setAttribute("loggedInCoach", coach);

        model.addAttribute("user", user);
        model.addAttribute("coach", coach);

        return "coach/schedule";
    }

    
    @ResponseBody
    @GetMapping("/api/schedules/my-schedules")
    public List<Map<String, Object>> getMySchedules(HttpSession session) {
        Coach coach = (Coach) session.getAttribute("loggedInCoach");
        if (coach == null) {
            return List.of();
        }
        List<Schedule> schedules = scheduleService.getSchedulesByCoach(coach);

        List<Map<String, Object>> result = new ArrayList<>();
        for (Schedule s : schedules) {
            Map<String, Object> event = new HashMap<>();
            event.put("id", s.getScheduleId());
            event.put("title", s.getTitle());
            String start = s.getStartDate() + "T" + s.getStartTime();
            String end = s.getStartDate() + "T" + s.getEndTime();
            event.put("start", start);
            event.put("end", end);
            event.put("location", s.getLocation());
            result.add(event);
        }
        return result;
    }
}