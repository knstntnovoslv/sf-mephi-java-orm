package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entities.*;
import org.example.entities.Module;
import org.example.repositories.CourseRepository;
import org.example.repositories.LessonRepository;
import org.example.repositories.ModuleRepository;
import org.example.repositories.QuizRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final QuizRepository quizRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;

    @Transactional
    public Course createCourse(Course course) {
        courseRepository.create(course);
        return course;
    }

    @Transactional
    public void addModule(Module module) {
        UUID courseId = module.getCourse().getId();
        Course course = courseRepository.get(courseId);
        module.setCourse(course);
        moduleRepository.create(module);
    }
    @Transactional
    public void deleteModule(UUID moduleId) {
        moduleRepository.delete(moduleId);
    }

    @Transactional
    public void addLesson(Lesson lesson) {
        UUID lessonId = lesson.getModule().getId();
        Module module = moduleRepository.get(lessonId);
        lesson.setModule(module);
        lessonRepository.create(lesson);
    }
    @Transactional
    public void deleteLesson(UUID lessonId) {
        lessonRepository.delete(lessonId);
    }

    public Course getCourse(UUID courseId) {
        return courseRepository.get(courseId);
    }
}
