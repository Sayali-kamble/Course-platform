package com.edtech.course_platform.repository;

import com.edtech.course_platform.entity.Subtopic;
import com.edtech.course_platform.entity.SubtopicProgress;
import com.edtech.course_platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubtopicProgressRepository extends JpaRepository<SubtopicProgress, Long> {
    Optional<SubtopicProgress> findByUserAndSubtopic(User user, Subtopic subtopic);
    List<SubtopicProgress> findByUser(User user);
}