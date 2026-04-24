package com.abhinav.interviewprep.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;  // ✅ CORRECT PLACE
import java.util.*;
@Entity
@Data
public class User {

    @ManyToMany
    @JoinTable(
        name = "user_favorites",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> favoriteQuestions;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Column
    private String role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}