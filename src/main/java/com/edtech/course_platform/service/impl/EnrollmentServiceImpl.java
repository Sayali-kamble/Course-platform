package com.edtech.course_platform.service.impl;

import com.edtech.course_platform.dto.EnrollmentResponse;
import com.edtech.course_platform.entity.Course;
import com.edtech.course_platform.entity.Enrollment;
import com.edtech.course_platform.entity.User;
import com.edtech.course_platform.exception.AlreadyEnrolledException;
import com.edtech.course_platform.exception.CourseNotFoundException;
import com.edtech.course_platform.exception.UserNotFoundException;
import com.edtech.course_platform.repository.CourseRepository;
import com.edtech.course_platform.repository.EnrollmentRepository;
import com.edtech.course_platform.repository.UserRepository;
import com.edtech.course_platform.service.EnrollmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Transactional
    public EnrollmentResponse enroll(String courseId, String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        if (enrollmentRepository.existsByUserAndCourse(user, course)) {
            throw new AlreadyEnrolledException("You are already enrolled in this course");
        }

        Enrollment enrollment = Enrollment.builder()
                .user(user)
                .course(course)
                .enrolledAt(Instant.now())
                .build();

        Enrollment saved = enrollmentRepository.save(enrollment);

        return EnrollmentResponse.builder()
                .enrollmentId(saved.getId())
                .courseId(course.getId().toString())
                .courseTitle(course.getTitle())
                .enrolledAt(saved.getEnrolledAt())
                .build();
    }
}

