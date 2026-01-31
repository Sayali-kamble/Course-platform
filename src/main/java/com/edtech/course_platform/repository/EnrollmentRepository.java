package com.edtech.course_platform.repository;

import com.edtech.course_platform.entity.Course;
import com.edtech.course_platform.entity.Enrollment;
import com.edtech.course_platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByUserAndCourse(User user, Course course);

    Optional<Enrollment> findByIdAndUserId(Long id, Long userId);
}
