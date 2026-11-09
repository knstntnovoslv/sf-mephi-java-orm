package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entities.Enrollment;
import org.example.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EnrollmentRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public Enrollment get(UUID id) {
        Enrollment enrollment = entityManager.find(Enrollment.class, id);
        if (enrollment == null) {
            throw new NotFoundException("Enrollment with id " + id + " not found");
        }
        return enrollment;
    }

    public List<Enrollment> getByUser(UUID userId) {
        return entityManager.createQuery(
                "SELECT e FROM Enrollment e WHERE e.user.id = :userId", Enrollment.class)
                    .setParameter("userId", userId)
                    .getResultList();
    }

    public List<Enrollment> getByCourse(UUID courseId) {
        return entityManager.createQuery(
                "SELECT e FROM Enrollment e WHERE e.course.id = :courseId", Enrollment.class)
                .setParameter("courseId", courseId)
                .getResultList();
    }

    public Enrollment getByUserAndCourse(UUID userId, UUID courseId) {
        return entityManager.createQuery(
                        "SELECT e FROM Enrollment e WHERE e.course.id = :courseId and e.user.id = :userId", Enrollment.class)
                .setParameter("courseId", courseId)
                .setParameter("userId", userId)
                .getResultList().get(0);
    }

    @Transactional
    public void create(Enrollment enrollment) {
        entityManager.persist(enrollment);
    }

    @Transactional
    public void update(Enrollment enrollment) {
        entityManager.merge(enrollment);
    }

    @Transactional
    public void delete(UUID id) {
        Enrollment enrollment = get(id);
        entityManager.remove(enrollment);
    }

}
