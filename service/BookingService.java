package com.pickellbal.service;

import com.pickellbal.model.Booking;
import com.pickellbal.model.User;
import com.pickellbal.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> findById(int id) {
        return bookingRepository.findById(id);
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteById(int id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> findByStudent(User student) {
        return bookingRepository.findByStudent(student);
    }
} 