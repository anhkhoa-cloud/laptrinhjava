package com.pickellbal.repository;

import com.pickellbal.model.StudentVideoProgress;
import com.pickellbal.model.TutorialVideo;
import com.pickellbal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentVideoProgressRepository extends JpaRepository<StudentVideoProgress, Integer> {
    // Có thể bổ sung các phương thức query tùy chỉnh ở đây nếu cần
    List<StudentVideoProgress> findByStudent(User student);

    Optional<StudentVideoProgress> findByStudentAndVideo(User student, TutorialVideo video);
    
} 