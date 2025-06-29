package com.pickellbal.repository;

import com.pickellbal.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {
    // Có thể bổ sung các phương thức query tùy chỉnh ở đây nếu cần
} 