package com.pickellbal.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;

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

    @Column(name = "booking_date", nullable = false)
    private Date bookingDate; // Sử dụng java.sql.Date

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime; // Đã đổi sang java.time.LocalTime

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime; // Đã đổi sang java.time.LocalTime

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.CHỜ_XÁC_NHẬN;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt; // Sử dụng java.sql.Timestamp

    public enum Status {
        CHỜ_XÁC_NHẬN, ĐÃ_XÁC_NHẬN, ĐÃ_HỦY, HOÀN_THÀNH
    }

    // Getters and setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }

    public Coach getCoach() { return coach; }
    public void setCoach(Coach coach) { this.coach = coach; }

    public Date getBookingDate() { return bookingDate; }
    public void setBookingDate(Date bookingDate) { this.bookingDate = bookingDate; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}