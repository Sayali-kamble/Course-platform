package com.edtech.course_platform.repository;

import com.edtech.course_platform.entity.Subtopic;
import com.edtech.course_platform.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubTopicRepository extends JpaRepository<Subtopic, String> {

    long countByTopic_Course_Id(String courseId);
}
