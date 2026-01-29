package com.edtech.course_platform.repository;

import com.edtech.course_platform.dto.CourseResponse;
import com.edtech.course_platform.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {

    @Query("""
        SELECT new com.edtech.course_platform.dto.CourseResponse(
            c.id,
            c.title,
            c.description,
            COUNT(DISTINCT t.id),
            COUNT(DISTINCT st.id)
        )
        FROM Course c
        LEFT JOIN c.topics t
        LEFT JOIN t.subtopics st
        GROUP BY c.id, c.title, c.description
    """)
    List<CourseResponse> findAllCoursesWithCounts();

    Optional<Course> findById(String id);
}