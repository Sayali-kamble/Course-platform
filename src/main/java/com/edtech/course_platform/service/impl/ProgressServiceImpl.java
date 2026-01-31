package com.edtech.course_platform.service.impl;

import com.edtech.course_platform.entity.Course;
import com.edtech.course_platform.entity.Subtopic;
import com.edtech.course_platform.entity.SubtopicProgress;
import com.edtech.course_platform.entity.User;
import com.edtech.course_platform.exception.EnrollmentRequiredException;
import com.edtech.course_platform.exception.SubtopicNotFoundException;
import com.edtech.course_platform.exception.UserNotFoundException;
import com.edtech.course_platform.repository.EnrollmentRepository;
import com.edtech.course_platform.repository.SubTopicRepository;
import com.edtech.course_platform.repository.SubtopicProgressRepository;
import com.edtech.course_platform.repository.UserRepository;
import com.edtech.course_platform.service.ProgressService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {
    private final UserRepository userRepository;
    private final SubTopicRepository subtopicRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final SubtopicProgressRepository progressRepository;

    @Transactional
    public SubtopicProgress markCompleted(
            String subtopicId,
            String userEmail
    ) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        Subtopic subtopic = subtopicRepository.findById(subtopicId)
                .orElseThrow(() ->
                        new SubtopicNotFoundException("Subtopic not found"));

        Course course = subtopic
                .getTopic()
                .getCourse();

        boolean enrolled =
                enrollmentRepository.existsByUserAndCourse(user, course);

        if (!enrolled) {
            throw new EnrollmentRequiredException(
                    "You must be enrolled in this course to mark subtopics as complete"
            );
        }


        return progressRepository
                .findByUserAndSubtopic(user, subtopic)
                .orElseGet(() -> {
                    SubtopicProgress progress =
                            SubtopicProgress.builder()
                                    .user(user)
                                    .subtopic(subtopic)
                                    .completedAt(Instant.now())
                                    .build();
                    return progressRepository.save(progress);
                });
    }
}
