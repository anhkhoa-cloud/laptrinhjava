package com.pickleball.khoa.admin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // đảm bảo đúng tên bảng trong CSDL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // nếu cột id trong DB là user_id
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash") // ánh xạ đúng tên cột trong DB
    private String password;

    // Getter và Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

        private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
