package com.edtech.course_platform.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class EnrollmentResponse {

    private Long enrollmentId;
    private String courseId;
    private String courseTitle;
    private Instant enrolledAt;
}
