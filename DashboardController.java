package com.pickleball.khoa.admin.controller;

import com.pickleball.khoa.admin.service.UserService;
import com.pickleball.khoa.admin.service.CoachService;
import com.pickleball.khoa.admin.service.TutorialVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @Autowired
    private CoachService coachService;

    @Autowired
    private TutorialVideoService videoService;

    @GetMapping("/admin/dashboard")
    public String showDashboard(Model model) {
        long userCount = userService.countAll();
        long coachCount = coachService.countAll();
        long videoCount = videoService.countAll();

        model.addAttribute("userCount", userCount);
        model.addAttribute("coachCount", coachCount);
        model.addAttribute("videoCount", videoCount);

        return "dashboard";
    }
}
