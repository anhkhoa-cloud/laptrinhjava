package com.pickellbal.controller;

import com.pickellbal.model.StudentVideoProgress;
import com.pickellbal.model.TutorialVideo;
import com.pickellbal.model.User;
import com.pickellbal.service.StudentVideoProgressService;
import com.pickellbal.service.TutorialVideoService;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/videos")
public class VideoController {
    @Autowired
    private TutorialVideoService tutorialVideoService;

    @Autowired
    private StudentVideoProgressService progressService;

    // @GetMapping("")
    // public String listVideos(Model model) {
    //     model.addAttribute("videos", tutorialVideoService.findAll());
    //     return "video_list";
    // }

    @GetMapping("/home")
    public String listVideos(Model model) {
        model.addAttribute("videos", tutorialVideoService.findAll());
        model.addAttribute("templateName", "video_home");
        model.addAttribute("fragmentName", "video_main");
        return "layout";
    }

    @GetMapping("/watch/{videoId}")
    public String viewVideo(@PathVariable("videoId") int videoId, Model model) {
        Optional<TutorialVideo> videoOpt = tutorialVideoService.findById(videoId);
        if (videoOpt.isPresent()) {
            model.addAttribute("video", videoOpt.get());
            model.addAttribute("templateName", "view_video");
            model.addAttribute("fragmentName", "watch_video");
        } else {
            // Xử lý trường hợp không tìm thấy video, ví dụ chuyển hướng hoặc báo lỗi
            return "error-page";
        }
        return "layout";
    }
    // @PostMapping("/completed/{videoId}")
    // public String Completed_video(@PathVariable int videoId, HttpSession session) {
    //     User student = (User) session.getAttribute("currentUser");
    //     if (student == null) {
    //         return "redirect:/auth/login";
    //     }

    //     progressService.markAsCompleted(student, videoId);

    //     return "redirect:/progress";
    // }

    @PostMapping("/completed/{videoId}")
    @ResponseBody
    public ResponseEntity<?> completedVideo(@PathVariable int videoId, HttpSession session) {
        User student = (User) session.getAttribute("currentUser");
        if (student == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Chưa đăng nhập");
        }
        progressService.markAsCompleted(student, videoId);
        return ResponseEntity.ok("Đã ghi nhận");
    }



    
} 