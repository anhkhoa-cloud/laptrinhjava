package com.example.pickleball.model;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "coaches")
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coachId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(length = 255)
    private String experience;

    @Column(length = 255)
    private String certifications;
    @Column(length = 255)
    private String imagePath;

    @Column(precision = 10, scale = 2)
    private BigDecimal hourlyRate;

    @Column(nullable = false)
    private Boolean isApproved = false;

    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
     public Coach() {
        // Đây là constructor mặc định, không cần làm gì trong thân hàm
    }


    public Coach(Integer coachId, User user, String bio, String experience, String certifications,
            BigDecimal hourlyRate, Boolean isApproved, Timestamp createdAt, Timestamp updatedAt) {
      
        this.user = user;
        this.bio = bio;
        this.experience = experience;
        this.certifications = certifications;
        this.hourlyRate = hourlyRate;
        this.isApproved = isApproved;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getExperience() {
        return experience;
    }
    

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
public Long getCoachId() {
    return coachId;
}

public void setCoachId(Long coachId) {
    this.coachId = coachId;
}
 
}
