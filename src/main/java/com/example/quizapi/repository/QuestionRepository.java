package com.example.quizapi.repository;

import com.example.quizapi.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM question ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Question findRandomQuestion();
}

