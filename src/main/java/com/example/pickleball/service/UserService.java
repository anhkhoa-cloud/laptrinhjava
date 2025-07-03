package com.example.pickleball.service;
import com.example.pickleball.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pickleball.model.User;

@Service
public class UserService {

    private final UserRepository userRepository;
    

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //Lấy user theo id
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    } 
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    public User login(String username, String password) { 
    User user = userRepository.findByUsername(username); 
    if (user != null && user.getPasswordHash().equals(password)) {
        return user;
    }
    return null;
}

}
