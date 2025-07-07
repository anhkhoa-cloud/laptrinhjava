package com.example.pickleball.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.pickleball.model.Booking;
import com.example.pickleball.model.Booking.Status;
import com.example.pickleball.model.Coach;
import com.example.pickleball.model.User;
import com.example.pickleball.service.BookingService;
import com.example.pickleball.service.CoachService;
import com.example.pickleball.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/coach/booking")
public class BookingController {

    @Autowired 
    BookingService bookingService;

    @Autowired 
    UserService userService;

    @Autowired 
    CoachService coachService;

    //Hiện danh sách booking
   @GetMapping
public String listBookings(HttpSession session, Model model) {
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        return "redirect:/login";
    }
    User user = userService.getUserById(userId);
    Coach coach = coachService.getCoachByUser(user);
    if (coach == null) {
        return "redirect:/login";
    }
    List<Booking> bookings = bookingService.getBookingsByCoach(coach);
    model.addAttribute("bookings", bookings);
    model.addAttribute("coach", coach);
    model.addAttribute("user", user);
    return "coach/booking";
}
    //thao tác xác nhận
      @PostMapping("/accept/{id}")
    public String acceptBooking(@PathVariable int id) {
        bookingService.getBookingById(id).ifPresent(booking -> {
            booking.setStatus(Status.ĐÃ_XÁC_NHẬN);
            bookingService.saveBooking(booking);
        });
        return "redirect:/coach/booking";
    }
    @PostMapping("/reject/{id}")
    public String rejectBooking(@PathVariable int id) {
        bookingService.getBookingById(id).ifPresent(booking -> {
            booking.setStatus(Status.ĐÃ_HỦY);
            bookingService.saveBooking(booking);
        });
        return "redirect:/coach/booking";
    }

}
