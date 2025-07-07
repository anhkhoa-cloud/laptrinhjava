package com.example.pickleball.service;

import com.example.pickleball.model.Booking;
import com.example.pickleball.model.Coach;
import com.example.pickleball.model.User;
import com.example.pickleball.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

   public List<Booking> getBookingsByCoach(Coach coach) {
        return bookingRepository.findByCoach(coach);
    }

    public Optional<Booking> getBookingById(int id) {
        return bookingRepository.findById(id);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
 
public List<Booking> getBookingsByStudentAndCoach(User student, Coach coach) {
    return bookingRepository.findByStudentAndCoach(student, coach);
}

 
}