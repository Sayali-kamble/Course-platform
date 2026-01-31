package com.edtech.course_platform.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EnrollmentProgressResponse {

    private Long enrollmentId;
    private String courseId;
    private String courseTitle;

    private long totalSubtopics;
    private long completedSubtopics;
    private double completionPercentage;

    private List<CompletedSubtopicDto> completedItems;
}