package com.pickellbal.controller;

import com.pickellbal.model.StudentVideoProgress;
import com.pickellbal.model.User;
import com.pickellbal.model.TutorialVideo;
import com.pickellbal.service.StudentVideoProgressService;
import com.pickellbal.service.UserService;
import com.pickellbal.service.TutorialVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/progress")
public class ProgressController {
    @Autowired
    private StudentVideoProgressService progressService;
    @Autowired
    private UserService userService;
    @Autowired
    private TutorialVideoService tutorialVideoService;

    @GetMapping("")
    public String progressList(Model model, HttpSession session) {
        User student = (User) session.getAttribute("currentUser");
        if (student == null) {
            return "redirect:/auth/login";
        }
        List<StudentVideoProgress> progresses = progressService.findByStudent(student);
        model.addAttribute("progresses", progresses);
        model.addAttribute("templateName", "progres_list");
        model.addAttribute("fragmentName", "progress_body");
        return "layout";
    }

    @PostMapping("/mark-completed")
    public String markCompleted(@ModelAttribute StudentVideoProgress progress, HttpSession session) {
        User student = (User) session.getAttribute("currentUser");
        if (student == null) {
            return "redirect:/auth/login";
        }
        progress.setStudent(student);
        progress.setCompleted(true);
        progressService.save(progress);
        return "redirect:/progress";
    }
} 