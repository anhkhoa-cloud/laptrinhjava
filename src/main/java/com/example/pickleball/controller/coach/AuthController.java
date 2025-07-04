package com.example.pickleball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pickleball.model.User;
import com.example.pickleball.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "coach/login"; 
    }

    @PostMapping("/login")
    public String login(
        @RequestParam String username,
        @RequestParam String password,
        HttpSession session,
        Model model) {
        
        User user = userService.login(username, password);
        
        if (user != null) {
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("role", user.getRole());
            return "redirect:/coach/dashboard";  
        } else {
            model.addAttribute("error", "Sai email hoặc mật khẩu!");
            return "coach/login";
        }
    }
}
