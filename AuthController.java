package com.pickleball.khoa.admin.controller;

import com.pickleball.khoa.admin.repository.UserRepository;
import com.pickleball.khoa.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                                @RequestParam String password,
                                Model model) {
        User user = userRepository.findByUsername(username).get();
        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Đăng nhập thất bại!");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
