package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entities.*;
import org.example.exceptions.AlreadyExistsException;
import org.example.exceptions.NotFoundException;
import org.example.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final CourseRepository courseRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AnswerOptionRepository answerOptionRepository;
    private final QuizSubmissionRepository quizSubmissionRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addCourseQuiz(Quiz quiz) {
        UUID courseId = quiz.getCourse().getId();
        Course course = courseRepository.get(courseId);
        quiz.setCourse(course);
        quizRepository.create(quiz);
    }
    @Transactional
    public void deleteQuiz(UUID quizId) {
        quizRepository.delete(quizId);
    }

    @Transactional
    public void addQuizQuestion(Question question) {
        UUID quizId = question.getQuiz().getId();
        Quiz quiz = quizRepository.get(quizId);
        question.setQuiz(quiz);
        questionRepository.create(question);
    }
    @Transactional
    public void deleteQuestion(UUID questionId) {
        questionRepository.delete(questionId);
    }

    @Transactional
    public void addAnswerOption(AnswerOption answerOption) {
        UUID questionId = answerOption.getQuestion().getId();
        Question question = questionRepository.get(questionId);
        answerOption.setQuestion(question);
        answerOptionRepository.create(answerOption);
    }
    @Transactional
    public void deleteAnswerOption(UUID answerOptionId) {
        answerOptionRepository.delete(answerOptionId);
    }

    @Transactional
    public void createQuizSubmission(QuizSubmission quizSubmission) {
        UUID quizId = quizSubmission.getQuiz().getId();
        UUID userId = quizSubmission.getUser().getId();

        try {
            QuizSubmission existingSubmission = quizSubmissionRepository.getByUserAndQuiz(userId, quizId);
            if (existingSubmission != null) {
                throw new AlreadyExistsException("Submission already exists");
            }
        } catch (NotFoundException ignored) {}

        Quiz quiz = quizRepository.get(quizId);
        User user = userRepository.get(userId);

        quizSubmission.setQuiz(quiz);
        quizSubmission.setUser(user);
        quizSubmissionRepository.create(quizSubmission);
    }

    public List<QuizSubmission> getByUser(UUID userId) {
        return quizSubmissionRepository.getByUser(userId);
    }

    public List<QuizSubmission> getByQuiz(UUID quizId) {
        return quizSubmissionRepository.getByQuiz(quizId);
    }
}
