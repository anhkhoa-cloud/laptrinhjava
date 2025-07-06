package com.example.pickleball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.pickleball.model.Coach;
import com.example.pickleball.model.User;
import com.example.pickleball.repository.CoachRepository;
import com.example.pickleball.repository.UserRepository;

import java.io.File;
import java.io.IOException;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    @Autowired
    private UserRepository userRepository;
    
    //Lấy coach theo user
    public Coach getCoachByUser(User user){
        return coachRepository.findByUser(user).orElse(null);
    }
    // Lấy coach theo id
    public Coach getCoachById(Long id) {
        return coachRepository.findById(id).orElse(null);
    }

    // Cập nhật thông tin coach và user, xử lý upload file
    public Coach updateCoachProfile(Integer userId, User userForm, Coach coachForm, MultipartFile avatarFile) throws IOException {
        User user = userRepository.findById(userId).orElseThrow();
        Coach coach = coachRepository.findByUser(user).orElse(null);

        // Update user
        user.setFullName(userForm.getFullName());
        user.setPhone(userForm.getPhone());
        user.setAddress(userForm.getAddress());
        user.setEmail(userForm.getEmail());
        userRepository.save(user);

        // Update coach
        coach.setBio(coachForm.getBio());
        coach.setExperience(coachForm.getExperience());
        coach.setCertifications(coachForm.getCertifications());
        coach.setHourlyRate(coachForm.getHourlyRate());

        // Upload file
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String uploadDir = "src/main/resources/static/uploads";
            String fileName = avatarFile.getOriginalFilename();
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) uploadPath.mkdirs();
            File dest = new File(uploadPath, fileName);
            avatarFile.transferTo(dest);
            coach.setImagePath("/uploads/" + fileName);
        }

        return coachRepository.save(coach);
    }

    public Coach saveCoach(Coach coach) { 
        return coachRepository.save(coach); 
    }


    public void registerCoach(User user, Coach coach, MultipartFile profileImage) {
        user.setRole(User.Role.COACH);
        user.setActive(true);
        User savedUser = userRepository.save(user);
        coach.setUser(savedUser);
        coachRepository.save(coach);
    }
      public Coach getCoachByUserId(Integer coachUserId) {
      return coachRepository.findByUser_UserId(coachUserId);
  }
   
}
