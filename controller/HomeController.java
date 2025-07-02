package com.pickellbal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("templateName", "index");
        model.addAttribute("fragmentName", "mainContent");
        return "layout";
    }
}
