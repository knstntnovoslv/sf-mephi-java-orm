package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.entities.QuizSubmission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class QuizSubmissionRepository extends BaseRepository<QuizSubmission> {

    @PersistenceContext
    private final EntityManager entityManager;

    public QuizSubmission getByUserAndQuiz(UUID userId, UUID quizId) {
        return entityManager.createQuery(
                        "SELECT s FROM Submission s WHERE s.user.id = :userId and s.quiz.id = :quizId", QuizSubmission.class)
                .setParameter("quizId", quizId)
                .setParameter("userId", userId)
                .getResultList().get(0);
    }

    public List<QuizSubmission> getByQuiz(UUID quizId) {
        return entityManager.createQuery(
                        "SELECT s FROM Submission s WHERE s.quiz.id = :quizId", QuizSubmission.class)
                .setParameter("quizId", quizId)
                .getResultList();
    }

    public List<QuizSubmission> getByUser(UUID userId) {
        return entityManager.createQuery(
                        "SELECT s FROM Submission s WHERE s.user.id = :userId", QuizSubmission.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
