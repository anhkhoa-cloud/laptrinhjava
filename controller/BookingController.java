package com.pickellbal.controller;

import com.pickellbal.model.Booking;
import com.pickellbal.model.User;
import com.pickellbal.repository.CoachRepository;
import com.pickellbal.model.Coach;
import com.pickellbal.service.BookingService;
import com.pickellbal.service.CoachService;
import com.pickellbal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private UserService userService;

    // @PostMapping("/new")
    // public String createBooking(@ModelAttribute Booking booking, HttpSession session) {
    //     User student = (User) session.getAttribute("currentUser");
    //     if (student == null) {
    //         return "redirect:/auth/login";
    //     }
    //     booking.setStudent(student);
    //     bookingService.save(booking);
    //     return "redirect:/bookings/history";
    // }
    // @PostMapping("/new")
    // public String createBooking(@ModelAttribute Booking booking, HttpSession session) {
    //     User student = (User) session.getAttribute("currentUser");
    //     if (student == null) return "redirect:/auth/login";

    //     // Lấy đúng coach từ DB
    //     Coach coach = coachService.findById(booking.getCoach().getCoachId())
    //                                 .orElseThrow(() -> new RuntimeException("Coach not found"));
    //     booking.setCoach(coach);

    //     booking.setStudent(student);
    //     bookingService.save(booking);
    //     return "redirect:/bookings/history";
    // }
    @Autowired
    private CoachRepository coachRepository;

    @PostMapping("/new")
    public String createBooking(@ModelAttribute Booking booking, HttpSession session) {
        User student = (User) session.getAttribute("currentUser");
        if (student == null) return "redirect:/auth/login";

        Coach coach = coachRepository.findById(booking.getCoach().getCoachId()).orElseThrow(() -> new RuntimeException("Coach not found"));

        booking.setCoach(coach);
        booking.setStudent(student);

        bookingService.save(booking);
        return "redirect:/bookings/history";
    }


    @GetMapping("/history")
    public String bookingHistory(Model model, HttpSession session) {
        User student = (User) session.getAttribute("currentUser");
        if (student == null) return "redirect:/auth/login";

        // Lấy booking của học viên hiện tại
        List<Booking> bookings = bookingService.findByStudent(student);
        model.addAttribute("bookings", bookings);
        return "booking_history";
    }
    @GetMapping("/schedule")
        public String scheduleWithLayout(Model model) {
        model.addAttribute("coaches", coachService.findAll());
        model.addAttribute("templateName", "booking_body");
        model.addAttribute("fragmentName", "infomation_coach");
        return "layout";
    }
} 