package com.pickleball.khoa.admin.controller;

import com.pickleball.khoa.admin.model.TutorialVideo;
import com.pickleball.khoa.admin.service.TutorialVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/videos")
public class TutorialVideoController {

    @Autowired
    private TutorialVideoService videoService;

    @GetMapping
    public String listVideos(Model model) {
        model.addAttribute("videos", videoService.getAllVideos());
        return "videos/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("video", new TutorialVideo());
        return "videos/add";
    }

    @PostMapping("/save")
    public String saveVideo(@ModelAttribute TutorialVideo video) {
        videoService.saveVideo(video);
        return "redirect:/admin/videos";
    }

    @GetMapping("/delete/{id}")
    public String deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return "redirect:/admin/videos";
    }
}
