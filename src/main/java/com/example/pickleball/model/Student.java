package com.example.pickleball.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address; 
    private String profilePictureUrl;
    private String password; // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
    private String role; // Ví dụ: "ROLE_STUDENT", "ROLE_COACH",    

}
