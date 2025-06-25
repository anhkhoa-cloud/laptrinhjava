package com.example.pickleball.controller.coach;

import com.example.pickleball.model.Booking;
import com.example.pickleball.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    // Lấy tất cả booking của một huấn luyện viên
    @GetMapping("/coach/{coachId}")
    public List<Booking> getBookingsByCoach(@PathVariable Long coachId) {
        return bookingService.getBookingsByCoachId(coachId);
    }

    // Lấy booking theo ID
    @GetMapping("/{id}")
    public Optional<Booking> getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    // Tạo mới booking
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.saveBooking(booking);
    }

    // Cập nhật booking
    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        booking.setBookingId(id);
        return bookingService.saveBooking(booking);
    }

    // Xóa booking
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }

    // Xác nhận booking
    @PostMapping("/{id}/confirm")
    public Booking confirmBooking(@PathVariable Long id) {
        return bookingService.confirmBooking(id);
    }

    // Từ chối booking
    @PostMapping("/{id}/reject")
    public Booking rejectBooking(@PathVariable Long id) {
        return bookingService.rejectBooking(id);
    }

    // Hoàn thành booking
    @PostMapping("/{id}/complete")
    public Booking completeBooking(@PathVariable Long id) {
        return bookingService.completeBooking(id);
    }

    // Hủy booking
    @PostMapping("/{id}/cancel")
    public Booking cancelBooking(@PathVariable Long id) {
        return bookingService.cancelBooking(id);
    }
}
