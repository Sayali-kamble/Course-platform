package com.edtech.course_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseDetailResponse {

    private String id;
    private String title;
    private String description;
    private List<TopicResponse> topics;
}
