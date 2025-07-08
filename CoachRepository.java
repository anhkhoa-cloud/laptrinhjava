package com.pickleball.khoa.admin.repository;

import com.pickleball.khoa.admin.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CoachRepository extends JpaRepository<Coach, Long> {
    List<Coach> findByIsApproved(boolean isApproved);
}
