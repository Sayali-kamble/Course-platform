package com.edtech.course_platform.repository;

import com.edtech.course_platform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}