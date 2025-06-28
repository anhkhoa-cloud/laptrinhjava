CREATE DATABASE pickleball;
USE pickleball;

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role ENUM('ADMIN', 'COACH', 'STUDENT') NOT NULL DEFAULT 'STUDENT',
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE coaches (
    coach_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL UNIQUE,
    bio TEXT,
    image_path VARCHAR(255),
    hourly_rate DECIMAL(10,2),
    experience VARCHAR(255),
    certifications VARCHAR(255),
    is_approved BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     pdated_at TIMESTAMP NULL DEFAULT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE tutorial_videos (
    video_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    video_type ENUM('YOUTUBE', 'UPLOAD') DEFAULT 'YOUTUBE',
    youtube_id VARCHAR(50),
    created_by INT,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users(user_id)
);

CREATE TABLE bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    coach_id INT NOT NULL,
    booking_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    status ENUM('CHỜ_XÁC_NHẬN', 'ĐÃ_XÁC_NHẬN', 'ĐÃ_HỦY', 'HOÀN_THÀNH') DEFAULT 'CHỜ_XÁC_NHẬN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES users(user_id),
    FOREIGN KEY (coach_id) REFERENCES coaches(coach_id)
);

CREATE TABLE student_video_progress (
    progress_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    video_id INT NOT NULL,
    is_completed BOOLEAN DEFAULT FALSE,
    completed_at DATETIME DEFAULT CURRENT_TIMESTAMP
    FOREIGN KEY (student_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (video_id) REFERENCES tutorial_videos(video_id) ON DELETE CASCADE,
    UNIQUE KEY unique_student_video (student_id, video_id)
);

INSERT INTO users (username, email, password_hash, full_name, role)
VALUES 
('admin01', 'admin@example.com', 'hashed_admin_pass', 'Quản trị viên A', 'ADMIN'),
('coach01', 'coach@example.com', 'hashed_coach_pass', 'Huấn luyện viên B', 'COACH'),
('student01', 'student@example.com', 'hashed_student_pass', 'Học viên C', 'STUDENT');


-- Giả sử user_id của coach01 là 2
INSERT INTO coaches (user_id, bio, hourly_rate, is_approved)
VALUES 
(2, 'Tôi là HLV Pickleball có 3 năm kinh nghiệm thi đấu và đào tạo.', 250000.00, TRUE);


-- created_by: user_id = 1 (admin)
INSERT INTO tutorial_videos (title, description, video_type, youtube_id, created_by)
VALUES
('Cách cầm vợt đúng', 'Video hướng dẫn cầm vợt Pickleball cơ bản', 'YOUTUBE', 'dQw4w9WgXcQ', 1),
('Kỹ thuật giao bóng cơ bản', 'Giao bóng đúng luật và kỹ thuật', 'YOUTUBE', 'eY52Zsg-KVI', 1),
('Chiến thuật thi đấu đôi', 'Các chiến thuật nâng cao trong thi đấu Pickleball', 'YOUTUBE', 'fJ9rUzIMcZQ', 1);


-- student_id = 3, coach_id = 1 (giả sử coach_id auto tăng là 1)
INSERT INTO bookings (student_id, coach_id, booking_date, start_time, end_time, status)
VALUES
(3, 1, '2025-07-01', '09:00:00', '10:00:00', 'CHỜ_XÁC_NHẬN'),
(3, 1, '2025-07-03', '14:00:00', '15:00:00', 'ĐÃ_XÁC_NHẬN');


INSERT INTO student_video_progress (student_id, video_id, is_completed, completed_at)
VALUES
(3, 1, TRUE, NOW()),
(3, 2, FALSE, NULL);
