package com.example.pickleball.controller;


import com.example.pickleball.model.User;
import com.example.pickleball.service.NotificationService;
import com.example.pickleball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationService service;
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public String list(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        
        User user = userService.getUserById(userId);
        if (user == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("notifications", service.getAll(user));
        model.addAttribute("unreadCount", service.getUnreadCount(user));
        return "coach/notification";
    }
}