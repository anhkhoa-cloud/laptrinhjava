package com.example.pickleball.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pickleball.model.Notification;
import com.example.pickleball.model.User;


    
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByUserOrderByCreatedAtDesc(User user);
    long countByUserAndStatus(User user, String status);
}
