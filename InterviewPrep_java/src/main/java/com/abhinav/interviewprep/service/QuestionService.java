package com.abhinav.interviewprep.service;

import com.abhinav.interviewprep.entity.Question;
import com.abhinav.interviewprep.entity.User;
import com.abhinav.interviewprep.repository.QuestionRepository;
import com.abhinav.interviewprep.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Existing method
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    // ✅ Existing method
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // 🔥 ADD THIS METHOD HERE
    public String addToFavorites(Long questionId, String email) {

        User user = userRepository.findByEmail(email);
        Question question = questionRepository.findById(questionId).orElse(null);

        if (user == null || question == null) {
            return "User or Question not found";
        }

        user.getFavoriteQuestions().add(question);
        userRepository.save(user);

        return "Added to favorites";
    }

    public String removeFromFavorites(Long questionId, String email) {

        User user = userRepository.findByEmail(email);
        Question question = questionRepository.findById(questionId).orElse(null);

        if (user == null || question == null) {
            return "User or Question not found";
        }

        user.getFavoriteQuestions().remove(question);
        userRepository.save(user);

        return "Removed from favorites";
    }

    // 🔥 ADD THIS ALSO
    public List<Question> getFavorites(String email) {
        User user = userRepository.findByEmail(email);
        return user.getFavoriteQuestions();
    }

    public List<Question> searchByTitle(String title) {
        return questionRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Question> getQuestionsByDifficulty(String difficulty) {
        return questionRepository.findByDifficulty(difficulty);
    }
}