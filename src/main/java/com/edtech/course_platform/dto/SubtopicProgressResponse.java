package com.edtech.course_platform.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@Builder
public class SubtopicProgressResponse {

    private String subtopicId;
    private boolean completed;
    private Instant completedAt;
}
