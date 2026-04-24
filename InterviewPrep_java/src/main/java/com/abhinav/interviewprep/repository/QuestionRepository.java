package com.abhinav.interviewprep.repository;

import com.abhinav.interviewprep.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;  // 🔥 ADD THIS

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByDifficulty(String difficulty);

    List<Question> findByTitleContainingIgnoreCase(String title);
}