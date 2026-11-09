package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.example.entities.Enrollment;
import org.example.entities.Submission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SubmissionRepository extends BaseRepository<Submission> {
    @PersistenceContext
    private final EntityManager entityManager;

    public Submission getByUserAssignment(UUID userId, UUID assignmentId) {
        return entityManager.createQuery(
                        "SELECT s FROM Submission s WHERE s.user.id = :userId and s.assignment.id = :assignmentId", Submission.class)
                .setParameter("assignmentId", assignmentId)
                .setParameter("userId", userId)
                .getResultList().get(0);
    }

    public List<Submission> getByAssignment(UUID assignmentId) {
        return entityManager.createQuery(
                        "SELECT s FROM Submission s WHERE s.assignment.id = :assignmentId", Submission.class)
                .setParameter("assignmentId", assignmentId)
                .getResultList();
    }
}
