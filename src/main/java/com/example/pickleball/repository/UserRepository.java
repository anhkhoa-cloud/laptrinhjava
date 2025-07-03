package com.example.pickleball.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pickleball.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
   
    Optional<User> findByUsernameAndPasswordHash(String username, String passwordHash);
    Optional<User> findByEmail(String username);
    User findByUsername(String username);
    
    
} 



