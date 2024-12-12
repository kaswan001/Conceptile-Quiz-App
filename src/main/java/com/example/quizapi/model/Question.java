package com.example.quizapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Question text cannot be blank")
    private String text;

    @ElementCollection
    @NotEmpty(message = "Options cannot be empty")
    @Size(min = 2, message = "At least two options are required")
    private List<String> options;

    private int correctOptionIndex;
}

