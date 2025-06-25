package com.example.pickleball.model;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    private String name;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;

    @Column(nullable = false)   
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;

    private String location;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private String status;

    // Constructors, getters, and setters
}
