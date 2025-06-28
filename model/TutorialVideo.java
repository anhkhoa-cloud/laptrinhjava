package com.pickleball.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tutorial_videos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorialVideo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Integer videoId;
    
    @Column(name = "title", nullable = false, length = 200)
    private String title;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "video_type")
    private VideoType videoType = VideoType.YOUTUBE;
    
    @Column(name = "youtube_id", length = 50)
    private String youtubeId;
    
    @Column(name = "file_url", length = 255)
    private String fileUrl;
    
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    public enum VideoType {
        YOUTUBE, UPLOAD
    }
} 