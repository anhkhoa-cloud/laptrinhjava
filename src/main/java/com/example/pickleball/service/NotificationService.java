package com.example.pickleball.service;

import com.example.pickleball.model.Notification;
import com.example.pickleball.model.User;
import com.example.pickleball.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationRepository repo;
    
    public List<Notification> getAll(User user) {
        return repo.findByUserOrderByCreatedAtDesc(user);
    }
    
    public long getUnreadCount(User user) {
        return repo.countByUserAndStatus(user, "UNREAD");
    }
    
    public void createSample(User user) {
        if (getUnreadCount(user) == 0) {
            repo.save(new Notification(user, "Chào mừng!", "Chào mừng đến với hệ thống"));
        }
    }
}
    
