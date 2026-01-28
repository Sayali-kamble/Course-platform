package com.edtech.course_platform.dto;

import lombok.Data;
import java.util.List;

@Data
public class CourseSeedDto {
    private String id;
    private String title;
    private String description;
    private List<TopicSeedDto> topics;
}

