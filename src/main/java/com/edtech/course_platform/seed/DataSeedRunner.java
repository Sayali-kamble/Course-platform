package com.edtech.course_platform.seed;

import com.edtech.course_platform.dto.CourseSeedDto;
import com.edtech.course_platform.dto.CourseSeedWrapper;
import com.edtech.course_platform.dto.SubTopicSeedDto;
import com.edtech.course_platform.dto.TopicSeedDto;
import com.edtech.course_platform.entity.Course;
import com.edtech.course_platform.entity.Subtopic;
import com.edtech.course_platform.entity.Topic;
import com.edtech.course_platform.repository.CourseRepository;
import com.edtech.course_platform.repository.SubTopicRepository;
import com.edtech.course_platform.repository.TopicRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeedRunner implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final TopicRepository topicRepository;
    private final SubTopicRepository subTopicRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {

        //Check if data already exists
        if (courseRepository.count() > 0) {
            return;
        }


        //Read course.json file from resources
        InputStream inputStream =
                new ClassPathResource("data/courses.json").getInputStream();

        CourseSeedWrapper wrapper =
                objectMapper.readValue(inputStream, CourseSeedWrapper.class);


        for (CourseSeedDto courseDto : wrapper.getCourses()) {

            Course course = Course.builder()
                    .id(courseDto.getId())
                    .title(courseDto.getTitle())
                    .description(courseDto.getDescription())
                    .build();

            courseRepository.save(course);


            for (TopicSeedDto topicDto : courseDto.getTopics()) {

                Topic topic = Topic.builder()
                        .id(topicDto.getId())
                        .title(topicDto.getTitle())
                        .course(course)
                        .build();

                topicRepository.save(topic);


                for (SubTopicSeedDto subTopicDto : topicDto.getSubtopics()) {

                    Subtopic subTopic = Subtopic.builder()
                            .id(subTopicDto.getId())
                            .title(subTopicDto.getTitle())
                            .content(subTopicDto.getContent())
                            .topic(topic)
                            .build();

                    subTopicRepository.save(subTopic);
                }
            }
        }

        log.info("Seed data loading completed successfully.");
    }
}
