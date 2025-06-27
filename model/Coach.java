package com.pickleball.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "coaches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coach {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coach_id")
    private Integer coachId;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    
    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;
    
    @Column(name = "image_path", length = 255)
    private String imagePath;
    
    @Column(name = "hourly_rate", precision = 10, scale = 2)
    private BigDecimal hourlyRate;
    
    @Column(name = "is_approved")
    private Boolean isApproved = false;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
} 