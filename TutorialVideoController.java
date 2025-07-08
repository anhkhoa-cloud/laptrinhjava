package com.pickleball.khoa.admin.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pickleball.khoa.admin.model.TutorialVideo;
import com.pickleball.khoa.admin.service.TutorialVideoService;

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

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("video", new TutorialVideo());
        return "videos/form";
    }

    @PostMapping
    public String createVideo(@ModelAttribute TutorialVideo video) {
        if (video.getCreatedAt() == null) {
            video.setCreatedAt(LocalDateTime.now());
        }
        videoService.saveVideo(video);
        return "redirect:/admin/videos";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        TutorialVideo video = videoService.getVideoById(id).orElse(null);
        if (video == null) {
            return "redirect:/admin/videos";
        }
        model.addAttribute("video", video);
        return "videos/form";
    }

    @PostMapping("/update/{id}")
    public String updateVideo(@PathVariable Long id, @ModelAttribute TutorialVideo video) {
        video.setVideoId(id);
        videoService.saveVideo(video);
        return "redirect:/admin/videos";
    }

    @GetMapping("/delete/{id}")
    public String deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
        return "redirect:/admin/videos";
    }
}
