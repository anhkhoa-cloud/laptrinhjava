package com.example.pickleball.controller;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.pickleball.model.Coach;
import com.example.pickleball.model.User;
import com.example.pickleball.service.CoachService;
import com.example.pickleball.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.pickleball.service.BookingService;
import com.example.pickleball.service.StudentVideoProgressService;
@Controller
@RequestMapping("/coach")
public class CoachProfileController {
    @Autowired
    private CoachService coachService;

    @Autowired
private UserService userService;

    @Autowired
    private BookingService bookingService;
    @Autowired
    private StudentVideoProgressService studentVideoProgressService;

     @GetMapping("/dashboard")  
    public String dashboard(){
        return "coach/dashboard";
    }
    //Xem trang thông tin cá nhân
    @GetMapping("/profile")
    public String viewProfile(HttpSession session, Model model){
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        Coach coach = coachService.getCoachByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("coach", coach);

        return "coach/profile";
    }
    //Hiển thị trang cập nhật thông tin
    @GetMapping("/update-profile")
    public String showUpdateProfile(HttpSession session, Model model){
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        Coach coach = coachService.getCoachByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("coach", coach);

        return "coach/update-profile";
    }
    
    
    //Cập nhật profile
    @PostMapping("/update-profile")
public String updateProfile(
        @ModelAttribute User userForm,
        @ModelAttribute Coach coachForm,
        @RequestParam(value="avatarFile", required=false) MultipartFile avatarFile,
        HttpSession session,
        Model model) {

    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        model.addAttribute("errorMessage", "Bạn chưa đăng nhập!");
        return "coach/update-profile";
    }

    User user = userService.getUserById(userId);
    Coach coach = coachService.getCoachByUser(user);

    if (user == null) {
        model.addAttribute("errorMessage", "Không tìm thấy người dùng để cập nhật.");
        return "coach/update-profile";
    }
    if (coach == null) {
        model.addAttribute("errorMessage", "Không tìm thấy hồ sơ huấn luyện viên để cập nhật.");
        return "coach/update-profile";
    }

    try {
        // Update thông tin chung
        user.setFullName(userForm.getFullName());
        user.setPhone(userForm.getPhone());
        user.setAddress(userForm.getAddress());
        user.setEmail(userForm.getEmail());
        userService.updateUser(user);

        // Update thông tin Coach
        coach.setBio(coachForm.getBio());
        coach.setExperience(coachForm.getExperience());
        coach.setCertifications(coachForm.getCertifications());
        coach.setHourlyRate(coachForm.getHourlyRate());

        // Xử lý upload file
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String uploadDir = "src/main/resources/static/uploads";
            String fileName = avatarFile.getOriginalFilename();

            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            File dest = new File(uploadPath, fileName);
            avatarFile.transferTo(dest);

            // Lưu đường dẫn ảnh vào DB
            coach.setImagePath("/uploads/" + fileName);
        }

        coachService.updateCoachProfile(coach);

        model.addAttribute("successMessage", "Cập nhật thành công");
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("errorMessage", "Cập nhật không thành công! " + e.getMessage());
    }

    model.addAttribute("user", user);
    model.addAttribute("coach", coach);

    return "coach/update-profile";
}























































    
    
    //Xem danh sách học viên
    @GetMapping("/students")
    public String students(HttpSession session, Model model){
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        User user = userService.getUserById(userId);
        Coach coach = coachService.getCoachByUser(user);
        model.addAttribute("students", bookingService.getStudentsByCoach(coach));
        return "coach/students";
    }

    //Xem thông tin chi tiết học viên
    @GetMapping("/student/{studentId}")
    public String studentDetail(@PathVariable int studentId, HttpSession session, Model model){
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        User student = userService.getUserById(studentId);
        Long completedVideos = studentVideoProgressService.countCompletedVideosByStudent(student);
        model.addAttribute("student", student);
        model.addAttribute("completedVideos", completedVideos);
        return "coach/student-detail";
    }

    //Xem thông tiến độ học tập
     @GetMapping("/student-process")
    public String StudentProcess(){
        return "coach/student-process";
    }
    //Giao bài tập
     @GetMapping("/assignment")
    public String assignment(){
        return "coach/assignment";
    }



}


//     //Xem hồ sơ cá nhân
//     @GetMapping("/profile")
//     public String viewProfile(HttpSession session, Model model) {
//         User user = (User) session.getAttribute("loggedInUser");

//         if (user == null) {
//             return "redirect:/login"; // Chưa đăng nhập thì về trang login
//         }

//         Coach coach = coachService.getCoachByUser(user);
//         if (coach == null) {
//             model.addAttribute("error", "Không tìm thấy hồ sơ huấn luyện viên!");
//             return "coach/profile";
//         }

//         model.addAttribute("coach", coach);
//         return "coach/profile"; 
//     }

//     //Xử lý cập nhật hồ sơ POST
//     @PostMapping("/profile/update")
//     public String updateProfile(@ModelAttribute("coach") Coach formCoach,
//                                 HttpSession session,
//                                 Model model) {
//         User user = (User) session.getAttribute("loggedInUser");

//         if (user == null) {
//             return "redirect:/login";
//         }

//         Coach coach = coachService.getCoachByUser(user);
//         if (coach == null) {
//             model.addAttribute("error", "Không tìm thấy hồ sơ để cập nhật!");
//             return "coach/profile";
//         }

//         // Cập nhật thông tin
//         coach.setBio(formCoach.getBio());
//         coach.setExperience(formCoach.getExperience());
//         coach.setCertifications(formCoach.getCertifications());
//         coach.setHourlyRate(formCoach.getHourlyRate());
//         coach.setImagePath(formCoach.getImagePath());

//         // Lưu lại
//         coachService.updateCoachProfile(coach);

//         model.addAttribute("coach", coach);
//         model.addAttribute("success", "Cập nhật hồ sơ thành công!");
//         return "coach/update-profile";
//     }
// }