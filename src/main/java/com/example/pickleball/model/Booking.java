package com.example.pickleball.model;

import jakarta.persistence.*;
import java.sql.*;

@Entity
@Table(name = "bookings")
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "coach_id", nullable = false)
    private Coach coach;

    @Column(nullable = false)
    private Date bookingDate;

    @Column(nullable = false)
    private Time startTime;

    @Column(nullable = false)
    private Time endTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status = Status.CHỜ_XÁC_NHẬN;

    @Column(nullable = false)
    private Timestamp createdAt;

    public enum Status {
        CHỜ_XÁC_NHẬN, ĐÃ_XÁC_NHẬN, ĐÃ_HỦY, HOÀN_THÀNH
    }

    

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }
}
