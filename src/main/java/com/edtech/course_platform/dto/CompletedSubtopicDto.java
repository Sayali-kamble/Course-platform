package com.edtech.course_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@AllArgsConstructor
@Data
public class CompletedSubtopicDto {

    private String subtopicId;
    private String subtopicTitle;
    private Instant completedAt;
}