package com.pickellbal.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "coaches")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coachId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(precision = 10, scale = 2)
    private Double hourlyRate;

    @Column(nullable = false)
    private boolean isApproved = false;

    @Column(nullable = false)
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters and setters
    public boolean isApproved() {
        return isApproved;
    }
    public void setApproved(boolean approved) {
        isApproved = approved;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public Double getHourlyRate() {
        return hourlyRate;
    }
    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    public int getCoachId() {
        return coachId;
    }

    // ...
} 