package com.edtech.course_platform.service.impl;

import com.edtech.course_platform.dto.CompletedSubtopicDto;
import com.edtech.course_platform.dto.EnrollmentProgressResponse;
import com.edtech.course_platform.entity.Course;
import com.edtech.course_platform.entity.Enrollment;
import com.edtech.course_platform.entity.SubtopicProgress;
import com.edtech.course_platform.entity.User;
import com.edtech.course_platform.exception.EnrollmentNotFoundException;
import com.edtech.course_platform.repository.EnrollmentRepository;
import com.edtech.course_platform.repository.SubTopicRepository;
import com.edtech.course_platform.repository.SubtopicProgressRepository;
import com.edtech.course_platform.service.EnrollmentProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentProgressServiceImpl implements EnrollmentProgressService {

    private final EnrollmentRepository enrollmentRepository;
    private final SubTopicRepository subtopicRepository;
    private final SubtopicProgressRepository progressRepository;

    public EnrollmentProgressResponse getProgress(
            Long enrollmentId,
            User user
    ) {
        Enrollment enrollment = enrollmentRepository
                .findByIdAndUserId(enrollmentId, user.getId())
                .orElseThrow(() ->
                        new EnrollmentNotFoundException("Enrollment not found")
                );

        Course course = enrollment.getCourse();

        long totalSubtopics =
                subtopicRepository.countByTopic_Course_Id(course.getId());

        List<SubtopicProgress> completed =
                progressRepository.findByUserIdAndSubtopic_Topic_Course_Id(
                        user.getId(),
                        course.getId()
                );

        long completedCount = completed.size();

        double percentage = totalSubtopics == 0
                ? 0
                : Math.round(
                (completedCount * 100.0 / totalSubtopics) * 100
        ) / 100.0;

        List<CompletedSubtopicDto> completedItems =
                completed.stream()
                        .map(p -> new CompletedSubtopicDto(
                                p.getSubtopic().getId(),
                                p.getSubtopic().getTitle(),
                                p.getCompletedAt()
                        ))
                        .toList();

        return EnrollmentProgressResponse.builder()
                .enrollmentId(enrollmentId)
                .courseId(course.getId())
                .courseTitle(course.getTitle())
                .totalSubtopics(totalSubtopics)
                .completedSubtopics(completedCount)
                .completionPercentage(percentage)
                .completedItems(completedItems)
                .build();
    }
}

