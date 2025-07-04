package com.example.pickleball.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
     private LocalDate startDate;
    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime; 

    @Column(nullable = false)
    private String location;


@ManyToOne
@JoinColumn(name = "coach_id")
private Coach coach;
    public Schedule (){

    }

    public Long getScheduleId() {
        return scheduleId;
    }



    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }



    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }



    public LocalDate getStartDate() {
        return startDate;
    }



    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }



    public LocalTime getStartTime() {
        return startTime;
    }



    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }



    public LocalTime getEndTime() {
        return endTime;
    }



    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }



    public String getLocation() {
        return location;
    }



    public void setLocation(String location) {
        this.location = location;
    }



    public Schedule(Long scheduleId, String title, LocalDate startDate, LocalTime startTime, LocalTime endTime,
            String location) {
        
        this.title = title;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }


    

    

}
