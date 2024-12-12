package com.example.quizapi.service;

import com.example.quizapi.model.*;
import com.example.quizapi.repository.QuestionRepository;
import com.example.quizapi.repository.QuizRepository;
import com.example.quizapi.repository.UserRepository;
import com.example.quizapi.exception.QuizApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public Quiz startNewQuiz(Long userId) {
        log.info("Starting new quiz for user with ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", userId);
                    return new QuizApiException("User not found with id: " + userId);
                });
        Quiz quiz = new Quiz();
        quiz.setUser(user);
        return quizRepository.save(quiz);
    }

    public Question getRandomQuestion() {
        log.info("Fetching a random question");
        Question question = questionRepository.findRandomQuestion();
        if (question == null) {
            log.error("No questions available in the database");
            throw new QuizApiException("No questions available in the database");
        }
        return question;
    }

    public QuizAnswer submitAnswer(Long quizId, Long questionId, int selectedOptionIndex) {
        log.info("Submitting answer for quiz: {}, question: {}, selected option: {}", quizId, questionId, selectedOptionIndex);
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> {
                    log.error("Quiz not found with id: {}", quizId);
                    return new QuizApiException("Quiz not found with id: " + quizId);
                });
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> {
                    log.error("Question not found with id: {}", questionId);
                    return new QuizApiException("Question not found with id: " + questionId);
                });

        if (selectedOptionIndex < 0 || selectedOptionIndex >= question.getOptions().size()) {
            log.error("Invalid option selected. Option index out of bounds: {}", selectedOptionIndex);
            throw new QuizApiException("Invalid option selected. Option index out of bounds.");
        }

        QuizAnswer answer = new QuizAnswer();
        answer.setQuestion(question);
        answer.setSelectedOptionIndex(selectedOptionIndex);
        answer.setCorrect(selectedOptionIndex == question.getCorrectOptionIndex());

        quiz.getAnswers().add(answer);
        quizRepository.save(quiz);

        return answer;
    }

    public QuizSummary getQuizSummary(Long quizId) {
        log.info("Generating summary for quiz: {}", quizId);
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> {
                    log.error("Quiz not found with id: {}", quizId);
                    return new QuizApiException("Quiz not found with id: " + quizId);
                });
        int totalQuestions = quiz.getAnswers().size();
        int correctAnswers = (int) quiz.getAnswers().stream().filter(QuizAnswer::isCorrect).count();

        return new QuizSummary(totalQuestions, correctAnswers, totalQuestions - correctAnswers);
    }
}

