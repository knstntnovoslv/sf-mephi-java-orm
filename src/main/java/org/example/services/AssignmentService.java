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
public class AssignmentService {
    private final LessonRepository lessonRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final SubmissionRepository submissionRepository;
    private final QuizSubmissionRepository quizSubmissionRepository;
    private final QuizRepository quizRepository;

    @Transactional
    public void createAssignment(Assignment assignment) {
        UUID lessonId = assignment.getLesson().getId();
        Lesson lesson = lessonRepository.get(lessonId);

        assignment.setLesson(lesson);
        assignmentRepository.create(assignment);
    }
    @Transactional
    public void deleteAssignment(UUID assignmentId) {
        assignmentRepository.delete(assignmentId);
    }

    @Transactional
    public void sendSubmission(Submission submission) {
        UUID assignmentId = submission.getAssignment().getId();
        UUID userId = submission.getUser().getId();

        try {
            Submission existingSubmission = submissionRepository.getByUserAssignment(userId, assignmentId);
            if (existingSubmission != null) {
                throw new AlreadyExistsException("Submission already exists");
            }
        } catch (NotFoundException ignored) {}

        Assignment assignment = assignmentRepository.get(assignmentId);
        User user = userRepository.get(userId);

        submission.setAssignment(assignment);
        submission.setUser(user);
        submissionRepository.create(submission);
    }

    public List<Submission> getSubmissions(UUID assignmentId) {
        return submissionRepository.getByAssignment(assignmentId);
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
}
