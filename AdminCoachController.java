package com.pickleball.khoa.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pickleball.khoa.admin.model.Coach;
import com.pickleball.khoa.admin.service.CoachService;

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

    @GetMapping("/admin/coaches/new")
    public String showCreateForm(Model model) {
        model.addAttribute("coach", new Coach());
        return "coaches/form";
    }

    @PostMapping("/admin/coaches")
    public String createCoach(@ModelAttribute("coach") Coach coach) {
        coachService.createCoach(coach);
        return "redirect:/admin/coaches";
    }

    @GetMapping("/admin/coaches/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        List<Coach> coaches = coachService.getAllCoaches();
        Coach coach = coaches.stream().filter(c -> c.getCoachId().equals(id)).findFirst().orElse(null);
        if (coach == null) {
            return "redirect:/admin/coaches";
        }
        model.addAttribute("coach", coach);
        return "coaches/form";
    }

    @PostMapping("/admin/coaches/update/{id}")
    public String updateCoach(@PathVariable("id") Long id, @ModelAttribute("coach") Coach coach) {
        coachService.updateCoach(id, coach);
        return "redirect:/admin/coaches";
    }

    @RequestMapping("/admin/coaches/delete/{id}")
    public String deleteCoach(@PathVariable("id") Long id) {
        coachService.deleteCoach(id);
        return "redirect:/admin/coaches";
    }
}
