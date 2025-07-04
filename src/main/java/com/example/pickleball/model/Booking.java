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

    @Column(nullable = false)
    private String location;


    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
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
    public Booking (){

    }
    public Booking(int bookingId, User student, Coach coach, Date bookingDate, Time startTime, Time endTime,
            Status status, Timestamp createdAt, String location) {
        this.bookingId = bookingId;
        this.student = student;
        this.coach = coach;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.createdAt = createdAt;
        this.location = location;
    }
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    public User getStudent() {
        return student;
    }



    public void setStudent(User student) {
        this.student = student;
    }



    public Coach getCoach() {
        return coach;
    }



    public void setCoach(Coach coach) {
        this.coach = coach;
    }
    

    public Date getBookingDate() {
        return bookingDate;
    }



    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }



    public Time getStartTime() {
        return startTime;
    }



    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }



    public Time getEndTime() {
        return endTime;
    }



    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }



    public Status getStatus() {
        return status;
    }



    public void setStatus(Status status) {
        this.status = status;
    }



    public Timestamp getCreatedAt() {
        return createdAt;
    }



    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    
}
