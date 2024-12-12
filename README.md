# Quiz API

This is a simple Quiz API built with Spring Boot and H2 Database, featuring custom exception handling and input validation.

## Features

- Start a new quiz session
- Get random multiple-choice questions
- Submit answers to questions
- Get a summary of quiz results
- Custom exception handling
- Input validation

## Assumptions

1. There is only one user in the system (for simplicity).
2. Questions are pre-seeded in the database.
3. The API doesn't handle user authentication or authorization.
4. Each quiz session is independent, and users can start multiple quizzes.
5. The application uses an in-memory H2 database, so data is not persisted between application restarts.

## API Endpoints

1. Start a new quiz session:
   - POST /api/quiz/start?userId={userId}
   - Returns a Quiz object with an ID to be used in subsequent requests
   - Validates that userId is provided and positive

2. Get a random multiple-choice question:
   - GET /api/quiz/question
   - Returns a Question object with text, options, and an ID

3. Submit an answer:
   - POST /api/quiz/{quizId}/submit?questionId={questionId}&selectedOptionIndex={selectedOptionIndex}
   - Returns a QuizAnswer object indicating whether the answer was correct
   - Validates that quizId, questionId are provided and positive, and selectedOptionIndex is valid

4. Get quiz summary:
   - GET /api/quiz/{quizId}/summary
   - Returns a QuizSummary object with total questions answered, correct answers, and incorrect answers
   - Validates that quizId is provided and positive

## Error Handling

The API now includes custom exception handling:

- `QuizApiException`: Thrown for application-specific errors (e.g., invalid input, resource not found)
- Global exception handler: Catches and formats all exceptions, providing detailed error messages

Error responses include:
- Timestamp
- Error message
- Error details

## Running the Application

1. Make sure you have Java 17 and Maven installed.
2. Clone this repository.
3. Navigate to the project directory and run: `mvn spring-boot:run`
4. The application will start on `http://localhost:8080`
5. You can access the H2 Console at `http://localhost:8080/h2-console` (use the JDBC URL, username, and password from `application.properties`)

## Testing the API

You can use tools like cURL, Postman, or any HTTP client to test the API endpoints.

Example workflow:

1. Start a new quiz: 
   `POST http://localhost:8080/api/quiz/start?userId=1`

2. Get a random question: 
   `GET http://localhost:8080/api/quiz/question`

3. Submit an answer: 
   `POST http://localhost:8080/api/quiz/{quizId}/submit?questionId={questionId}&selectedOptionIndex={selectedOptionIndex}`

4. Get quiz summary: 
   `GET http://localhost:8080/api/quiz/{quizId}/summary`

Replace `{quizId}`, `{questionId}`, and `{selectedOptionIndex}` with actual values from your requests.

## Note on Data Persistence

As mentioned in the assumptions, this application uses an in-memory H2 database. This means that all data (including seeded questions and user information) will be reset when the application is restarted. If you need to persist data between application restarts, consider using a persistent database like MySQL or PostgreSQL.

## Extending the Application

To extend this application, you might consider:

1. Adding user authentication and authorization
2. Implementing persistent data storage
3. Creating a front-end interface for the quiz
4. Adding more question types (e.g., true/false, multiple select)
5. Implementing quiz categories or difficulty levels
6. Adding more comprehensive error handling and logging
7. Implementing rate limiting to prevent abuse of the API

## Troubleshooting

If you encounter any issues while running the application:

1. Ensure you're using Java 17 or later
2. Check that all dependencies are correctly installed
3. Verify that the port 8080 is not in use by another application
4. Review the console output for any error messages
5. Check the API responses for detailed error messages in case of failures

If problems persist, please open an issue in the project repository with details about the error and steps to reproduce it.

## Contributing

Contributions to this project are welcome. Please fork the repository and submit a pull request with your changes.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

