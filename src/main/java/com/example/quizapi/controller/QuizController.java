package com.example.quizapi.controller;

import com.example.quizapi.model.Question;
import com.example.quizapi.model.Quiz;
import com.example.quizapi.model.QuizAnswer;
import com.example.quizapi.model.QuizSummary;
import com.example.quizapi.service.QuizService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/start")
    public ResponseEntity<Quiz> startNewQuiz(@RequestParam @NotNull @Min(1) Long userId) {
        return ResponseEntity.ok(quizService.startNewQuiz(userId));
    }

    @GetMapping("/question")
    public ResponseEntity<Question> getRandomQuestion() {
        return ResponseEntity.ok(quizService.getRandomQuestion());
    }

    @PostMapping("/{quizId}/submit")
    public ResponseEntity<QuizAnswer> submitAnswer(
            @PathVariable @NotNull @Min(1) Long quizId,
            @RequestParam @NotNull @Min(1) Long questionId,
            @RequestParam @Min(0) int selectedOptionIndex) {
        return ResponseEntity.ok(quizService.submitAnswer(quizId, questionId, selectedOptionIndex));
    }

    @GetMapping("/{quizId}/summary")
    public ResponseEntity<QuizSummary> getQuizSummary(@PathVariable @NotNull @Min(1) Long quizId) {
        return ResponseEntity.ok(quizService.getQuizSummary(quizId));
    }
}

