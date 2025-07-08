package com.pickleball.khoa.admin.repository;

import com.pickleball.khoa.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Bạn có thể thêm các hàm tìm kiếm tùy chỉnh nếu cần
    Optional<User> findByUsername(String username);
}
