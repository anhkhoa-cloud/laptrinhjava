package com.pickleball.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_video_progress")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentVideoProgress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    private Integer progressId;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;
    
    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private TutorialVideo video;
    
    @Column(name = "is_completed")
    private Boolean isCompleted = false;
    
    @Column(name = "completed_at")
    private LocalDateTime completedAt;
} 