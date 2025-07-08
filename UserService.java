package com.pickleball.khoa.admin.service;

import com.pickleball.khoa.admin.model.User;
import com.pickleball.khoa.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Lấy danh sách tất cả người dùng
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lưu hoặc cập nhật người dùng
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Lấy người dùng theo ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Xóa người dùng theo ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Đếm tổng số người dùng
    public long countAll() {
        return userRepository.count();
    }

}
