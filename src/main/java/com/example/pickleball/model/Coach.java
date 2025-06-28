package com.example.pickleball.model;

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
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String phone;
    
    @Column(length = 1000)
    private String bio;
    
    @Column
    private String experience;
    
    @Column
    private String specialization;
    
    @Column
    private String imageUrl;
    
    @Column(nullable = false)
    private boolean active = true;
    
    // Thêm các trường mới cho Coach
    @Column(precision = 3, scale = 2)
    private BigDecimal rating = BigDecimal.ZERO;
    
    @Column
    private Integer totalReviews = 0;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal hourlyRate;
    
    @Column(length = 500)
    private String availability; // JSON string hoặc text mô tả
    
    @Column
    private String certifications; // Chứng chỉ, bằng cấp
    
    @Column
    private String location; // Địa điểm dạy
    
    @Column
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 