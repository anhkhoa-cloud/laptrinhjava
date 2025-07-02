package com.pickleball.khoa.admin.controller;

import com.pickleball.khoa.admin.model.Coach;
import com.pickleball.khoa.admin.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminCoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping("/admin/coaches")
    public String listCoaches(Model model) {
        List<Coach> coaches = coachService.getAllCoaches();
        model.addAttribute("coaches", coaches);
        return "coaches/list"; // maps to templates/coaches/list.html
    }
}
