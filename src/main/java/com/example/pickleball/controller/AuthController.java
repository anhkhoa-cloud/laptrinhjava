package com.example.pickleball.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.pickleball.model.Coach;
import com.example.pickleball.model.User;
import com.example.pickleball.service.CoachService;
import com.example.pickleball.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CoachService coachService;

    // Hiển thị trang đăng nhập
    @GetMapping("/login")
    public String showLoginPage(
            @RequestParam(value = "registerSuccess", required = false) String registerSuccess,
            Model model) {
        if (registerSuccess != null && registerSuccess.equals("true")) {
            model.addAttribute("successMessage", "Đăng ký tài khoản thành công! Vui lòng đăng nhập.");
        }
        return "coach/login";
    }

    // Xử lý yêu cầu đăng nhập
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        User user = userService.login(username, password);

        if (user != null) {
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("role", user.getRole());
            // Chuyển hướng đến dashboard của HLV
            return "redirect:/coach/dashboard";
        } else {
            model.addAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu!");
            return "coach/login";
        }
    }

    // Hiển thị trang đăng ký HLV
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("coach", new Coach());
        return "coach/register";
    }

    // Xử lý yêu cầu đăng ký HLV
    @PostMapping("/register")
    public String register(
            @ModelAttribute("user") User user,
            @ModelAttribute("coach") Coach coach,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
            Model model) {

        // Kiểm tra trùng username
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("errorMessage", "Tên đăng nhập đã tồn tại!");
            model.addAttribute("user", user);
            model.addAttribute("coach", coach);
            return "coach/register";
        }

        // Kiểm tra trùng email
        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("errorMessage", "Email đã tồn tại!");
            model.addAttribute("user", user);
            model.addAttribute("coach", coach);
            return "coach/register";
        }

        try {
            // Gọi service để xử lý toàn bộ logic đăng ký (lưu user, coach, ảnh)
            coachService.registerCoach(user, coach, profileImage);

            model.addAttribute("successMessage", "Đăng ký thành công! Vui lòng đăng nhập.");
            return "redirect:/login?registerSuccess=true";

        } catch (Exception e) {
            // Bắt và xử lý lỗi trong quá trình đăng ký
            model.addAttribute("errorMessage", "Đăng ký thất bại: " + e.getMessage());
            model.addAttribute("user", user);
            model.addAttribute("coach", coach);
            e.printStackTrace(); // In lỗi ra console để debug
            return "coach/register";
        }
    }
    // Hiển thị form đổi mật khẩu
    @GetMapping("coach/change-password")
    public String showChangePasswordPage() {
        return "coach/change-password";
    }

    // Xử lý đổi mật khẩu
    @PostMapping("coach/change-password")
    public String processChangePassword(
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            HttpSession session,
            Model model) {
        Integer userId = (Integer )session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        User user = userService.findById(userId);

        if (user == null || !userService.checkPassword(user, oldPassword)) {
            model.addAttribute("errorMessage", "Mật khẩu cũ không đúng!");
            return "coach/change-password";
        }
        userService.updatePassword(user, newPassword);
        model.addAttribute("successMessage", "Đổi mật khẩu thành công!");
        return "coach/change-password";
    }
       
    //Đăng xuất 
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate(); // Xóa toàn bộ dữ liệu trong session

        // Xóa cookies liên quan nếu cần (ví dụ: để làm sạch session hoặc token)
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);  // Thiết lập thời gian sống của cookie là 0, để xóa cookie
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/login"; // Chuyển hướng về trang login
    }
    }