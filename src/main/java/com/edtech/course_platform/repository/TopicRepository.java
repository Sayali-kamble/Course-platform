package com.edtech.course_platform.repository;

import com.edtech.course_platform.entity.Course;
import com.edtech.course_platform.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, String> {

}