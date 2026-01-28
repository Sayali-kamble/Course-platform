package com.edtech.course_platform.dto;

import lombok.Data;
import java.util.List;

@Data
public class TopicSeedDto {
    private String id;
    private String title;
    private List<SubTopicSeedDto> subtopics;
}
