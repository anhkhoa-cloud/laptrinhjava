package com.example.pickleball.service;

import com.example.pickleball.model.Booking;
import com.example.pickleball.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pickleball.model.BookingStatus;


import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    // Lấy tất cả booking của một huấn luyện viên
    public List<Booking> getBookingsByCoachId(Long coachId) {
        return bookingRepository.findByCoachId(coachId);
    }

    // Lấy booking theo ID
    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    // Thêm hoặc cập nhật booking
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Xóa booking
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    // Xác nhận booking
    public Booking confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.setStatus(BookingStatus.CONFIRMED);
        return bookingRepository.save(booking);
    }

    // Từ chối booking
    public Booking rejectBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.setStatus(BookingStatus.REJECTED);
        return bookingRepository.save(booking);
    }

    // Hoàn thành booking
    public Booking completeBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.setStatus(BookingStatus.COMPLETED);
        return bookingRepository.save(booking);
    }

    // Hủy booking
    public Booking cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.setStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }
}
