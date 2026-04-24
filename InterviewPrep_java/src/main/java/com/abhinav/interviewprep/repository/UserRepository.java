package com.abhinav.interviewprep.repository;

import com.abhinav.interviewprep.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    boolean existsByEmail(String email); // 🔥 REQUIRED

}