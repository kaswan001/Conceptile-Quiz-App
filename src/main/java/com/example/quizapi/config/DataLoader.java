package com.example.quizapi.config;

import com.example.quizapi.model.Question;
import com.example.quizapi.model.User;
import com.example.quizapi.repository.QuestionRepository;
import com.example.quizapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        // Seed a single user
        User user = new User();
        user.setUsername("quizuser");
        userRepository.save(user);

        // Seed questions
        seedQuestions();
    }

    private void seedQuestions() {
        createQuestion("What is the capital of France?", 
            Arrays.asList("London", "Berlin", "Paris", "Madrid"), 2);
        
        createQuestion("Which planet is known as the Red Planet?", 
            Arrays.asList("Venus", "Mars", "Jupiter", "Saturn"), 1);
        
        createQuestion("Who painted the Mona Lisa?", 
            Arrays.asList("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"), 2);
        
        createQuestion("What is the largest ocean on Earth?", 
            Arrays.asList("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"), 3);
        
        createQuestion("Which element has the chemical symbol 'O'?", 
            Arrays.asList("Gold", "Silver", "Oxygen", "Osmium"), 2);
        
        createQuestion("In what year did World War II end?", 
            Arrays.asList("1943", "1944", "1945", "1946"), 2);
        
        createQuestion("What is the capital of Japan?", 
            Arrays.asList("Seoul", "Beijing", "Tokyo", "Bangkok"), 2);
        
        createQuestion("Who wrote 'Romeo and Juliet'?", 
            Arrays.asList("Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain"), 1);
        
        createQuestion("What is the largest planet in our solar system?", 
            Arrays.asList("Earth", "Mars", "Jupiter", "Saturn"), 2);
        
        createQuestion("Which country is home to the kangaroo?", 
            Arrays.asList("New Zealand", "South Africa", "Australia", "Brazil"), 2);
    }

    private void createQuestion(String text, List<String> options, int correctOptionIndex) {
        Question question = new Question();
        question.setText(text);
        question.setOptions(options);
        question.setCorrectOptionIndex(correctOptionIndex);
        questionRepository.save(question);
    }
}

