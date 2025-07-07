package com.pickellbal.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "student_video_progress",
    uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "video_id"}))
public class StudentVideoProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int progressId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private TutorialVideo video;

    @Column(name = "is_completed",nullable = false)
    private boolean isCompleted = false;

    @Column(name = "completed_at")
    private Timestamp completedAt;

    // Getters and setters
    public User getStudent() {
        return student;
    }
    public void setStudent(User student) {
        this.student = student;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
    // ...
    public int getProgressId() {
        return progressId;
    }
    public void setVideo(TutorialVideo video) {
        this.video = video;
    }
    public TutorialVideo getVideo() {
        return video;
    }
    public void setCompletedAt(Timestamp completedAt) {
        this.completedAt = completedAt;
    }
    public Timestamp getCompletedAt() {
        return completedAt;
    }
} 