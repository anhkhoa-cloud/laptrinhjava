package com.example.pickleball.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pickleball.model.Booking;
import com.example.pickleball.model.Coach;
import com.example.pickleball.repository.BookingRepository;


@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

   public List<Booking> getBookingsByCoach(Coach coach) {
        return bookingRepository.findByCoach(coach);
    }

}
