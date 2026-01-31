package com.edtech.course_platform.service;

import com.edtech.course_platform.entity.Course;
import com.edtech.course_platform.entity.Subtopic;
import com.edtech.course_platform.entity.SubtopicProgress;
import com.edtech.course_platform.entity.User;
import com.edtech.course_platform.repository.EnrollmentRepository;
import com.edtech.course_platform.repository.SubTopicRepository;
import com.edtech.course_platform.repository.SubtopicProgressRepository;
import com.edtech.course_platform.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;


public interface ProgressService {

    public SubtopicProgress markCompleted(
            String subtopicId,
            String userEmail
    );
}
