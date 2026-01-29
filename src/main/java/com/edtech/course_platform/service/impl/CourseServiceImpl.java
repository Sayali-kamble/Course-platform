package com.edtech.course_platform.service.impl;

import com.edtech.course_platform.dto.CourseDetailResponse;
import com.edtech.course_platform.dto.CourseResponse;
import com.edtech.course_platform.dto.SubTopicResponse;
import com.edtech.course_platform.dto.TopicResponse;
import com.edtech.course_platform.entity.Course;
import com.edtech.course_platform.entity.Subtopic;
import com.edtech.course_platform.entity.Topic;
import com.edtech.course_platform.exception.CourseNotFoundException;
import com.edtech.course_platform.repository.CourseRepository;
import com.edtech.course_platform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAllCoursesWithCounts();
    }

    @Override
    public CourseDetailResponse getCourseById(String courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        List<TopicResponse> topicResponses = course.getTopics()
                .stream()
                .map(this::mapToTopicResponse)
                .collect(Collectors.toList());

        return new CourseDetailResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                topicResponses
        );
    }

    private TopicResponse mapToTopicResponse(Topic topic) {

        List<SubTopicResponse> subTopicResponses = topic.getSubtopics()
                .stream()
                .map(this::mapToSubTopicResponse)
                .collect(Collectors.toList());

        return new TopicResponse(
                topic.getId(),
                topic.getTitle(),
                subTopicResponses
        );
    }

    private SubTopicResponse mapToSubTopicResponse(Subtopic subTopic) {
        return new SubTopicResponse(
                subTopic.getId(),
                subTopic.getTitle(),
                subTopic.getContent()
        );
    }
}
