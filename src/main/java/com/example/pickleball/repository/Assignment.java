package com.example.pickleball.model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate assignedDate;
    @Column(nullable = false)
    private LocalDate dueDate;
    
    @Column(nullable = false)
    private boolean completed;
    @Column(name = "attachment_path")
    private String attachmentPath;

    @Column(name = "attachment_link")
private String attachmentLink;


    @ManyToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name = "user_id", nullable = false) 
    private User coach; 

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    
    public Assignment(){

    }



    public Assignment(Long id, String title, String description, LocalDate assignedDate, LocalDate dueDate,
            boolean completed, String attachmentPath, String attachmentLink, User coach, User student) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.completed = completed;
        this.attachmentPath = attachmentPath;
        this.attachmentLink = attachmentLink;
        this.coach = coach;
        this.student = student;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    public User getCoach() {
        return coach;
    }


    public void setCoach(User coach) {
        this.coach = coach;
    }


    public User getStudent() {
        return student;
    }


    public void setStudent(User student) {
        this.student = student;
    }




    public String getAttachmentPath() {
        return attachmentPath;
    }




    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }
    
    public String getAttachmentLink() {
    return attachmentLink;
}
public void setAttachmentLink(String attachmentLink) {
    this.attachmentLink = attachmentLink;
}


    
}

  

   
