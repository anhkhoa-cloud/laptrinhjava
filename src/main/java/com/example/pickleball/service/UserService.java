package com.example.pickleball.service;
import com.example.pickleball.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;


import org.springframework.stereotype.Service;

import com.example.pickleball.model.User;
import com.example.pickleball.model.User.Role;

@Service
public class UserService {

    private final UserRepository userRepository;
    

   public UserService(UserRepository userRepository) {
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

     public List<User> getAllUsersByRole(Role role) { 
        return userRepository.findByRole(role);
}
     public void saveUser(User user) {
        userRepository.save(user);
        
     }
     public void deleteUser(int id) {
         userRepository.deleteById(id);
        
     }
     
     // Xử lý đăng ký
    public void registerNewCoach(String fullName, String email, String phone, String address, String password) {
    if (userRepository.findByEmail(email) != null) {
        throw new RuntimeException("Email đã được sử dụng!");
    }

    User user = new User();
    user.setFullName(fullName);
    user.setEmail(email);
    user.setPhone(phone);
    user.setAddress(address);
    user.setPasswordHash(password); 
    user.setRole(User.Role.COACH);

    userRepository.save(user);
}
public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username) != null;
    }

 public boolean existsByEmail(String email) {
    return userRepository.findByEmail(email).isPresent();
}

    public void register(User user) {
        userRepository.save(user);
    }
    public User findById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
    public boolean checkPassword(User user, String oldPassword) {
       
         return user.getPasswordHash().equals(oldPassword);
    }
    public void updatePassword(User user, String newPassword) {
       
       user.setPasswordHash(newPassword);
        userRepository.save(user);
    }
 
    public int countAllStudents() {
    return userRepository.findByRole(User.Role.STUDENT).size();
}

}
