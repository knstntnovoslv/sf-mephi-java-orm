package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entities.Lesson;
import org.example.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LessonRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public Lesson get(UUID id) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        if (lesson == null) {
            throw new NotFoundException("Lesson with id " + id + " not found");
        }
        return lesson;
    }

    public List<Lesson> getByCourse(UUID courseId) {
        return entityManager
                .createQuery("SELECT l FROM Lesson l WHERE l.course.id = :courseId", Lesson.class)
                .setParameter("courseId", courseId)
                .getResultList();
    }

    @Transactional
    public void create(Lesson lesson) {
        entityManager.persist(lesson);
    }

    @Transactional
    public void update(Lesson lesson) {
        entityManager.merge(lesson);
    }

    @Transactional
    public void delete(UUID id) {
        Lesson lesson = get(id);
        entityManager.remove(lesson);
    }
}
