package com.example.pickleball.controller.coach;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CoachController {
    @GetMapping("/coach")
    public String CoachPage() {
        return "coach/index";   
    }
    
}
