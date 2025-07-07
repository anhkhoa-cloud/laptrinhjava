package com.example.pickleball.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pickleball.model.User;
import com.example.pickleball.model.User.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
   
    Optional<User> findByUsernameAndPasswordHash(String username, String passwordHash);
    User findByUsername(String username);
    List<User> findByRole(Role role);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserId(Integer coachId);
    
    
} 



