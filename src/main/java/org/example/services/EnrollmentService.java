package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entities.Course;
import org.example.entities.Enrollment;
import org.example.entities.User;
import org.example.repositories.CourseRepository;
import org.example.repositories.EnrollmentRepository;
import org.example.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public void createEnrollment(UUID userId, UUID courseId) {
        Enrollment enrollment = new Enrollment();
        User user = userRepository.get(userId);
        Course course = courseRepository.get(courseId);

        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollmentRepository.create(enrollment);
    }

    @Transactional
    public void deleteEnrollment(UUID userId, UUID courseId) {
        Enrollment enrollment = enrollmentRepository.getByUserAndCourse(userId, courseId);
        enrollmentRepository.delete(enrollment.getId());
    }

    public List<Enrollment> getUserEnrollments(UUID userId) {
        return enrollmentRepository.getByUser(userId);
    }

    public List<Enrollment> getCourseEnrollments(UUID courseId) {
        return enrollmentRepository.getByCourse(courseId);
    }
}
