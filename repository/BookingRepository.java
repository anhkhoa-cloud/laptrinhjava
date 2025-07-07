package com.pickellbal.repository;

import com.pickellbal.model.Booking;
import com.pickellbal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    // Có thể bổ sung các phương thức query tùy chỉnh ở đây nếu cần
    List<Booking> findByStudent(User student);
} 