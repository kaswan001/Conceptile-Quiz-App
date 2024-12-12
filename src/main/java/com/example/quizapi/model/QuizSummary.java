package com.example.quizapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizSummary {
    private int totalQuestions;
    private int correctAnswers;
    private int incorrectAnswers;
}

