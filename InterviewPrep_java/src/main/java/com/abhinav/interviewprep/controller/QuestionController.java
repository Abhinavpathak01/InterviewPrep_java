package com.abhinav.interviewprep.controller;

import com.abhinav.interviewprep.entity.Question;
import com.abhinav.interviewprep.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // ➕ Add question
    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PostMapping("/{id}/favorite")
    public String addToFavorites(@PathVariable Long id) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return questionService.addToFavorites(id, email);
    }

    @GetMapping("/favorites")
    public List<Question> getFavorites() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return questionService.getFavorites(email);
    }

    // 📚 Get all questions
    @GetMapping
    public List<Question> getQuestions(
            @RequestParam(required = false) String difficulty,
            @RequestParam(required = false) String title) {

        if (title != null) {
            return questionService.searchByTitle(title);
        }

        if (difficulty != null) {
            return questionService.getQuestionsByDifficulty(difficulty);
        }

        return questionService.getAllQuestions();
    }

    @DeleteMapping("/{id}/favorite")
    public String removeFromFavorites(@PathVariable Long id) {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return questionService.removeFromFavorites(id, email);
    }
}