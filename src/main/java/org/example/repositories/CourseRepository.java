package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entities.Course;
import org.example.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CourseRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public Course get(UUID id) {
        Course course = entityManager.find(Course.class, id);
        if (course == null) {
            throw new NotFoundException("Course with id " + id + " not found");
        }
        return course;
    }

    @Transactional
    public void create(Course course) {
        entityManager.persist(course);
    }

    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Transactional
    public void delete(UUID id) {
        Course course = get(id);
        entityManager.remove(course);
    }
}
