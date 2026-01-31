package com.edtech.course_platform.controller;

import com.edtech.course_platform.dto.SubtopicProgressResponse;
import com.edtech.course_platform.entity.SubtopicProgress;
import com.edtech.course_platform.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subtopics")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/{subtopicId}/complete")
    public ResponseEntity<SubtopicProgressResponse> completeSubtopic(
            @PathVariable String subtopicId,
            Authentication authentication
    ) {
        String email = authentication.getName();

        SubtopicProgress progress =
                progressService.markCompleted(subtopicId, email);

        SubtopicProgressResponse response =
                SubtopicProgressResponse.builder()
                        .subtopicId(progress.getSubtopic().getId())
                        .completed(true)
                        .completedAt(progress.getCompletedAt())
                        .build();

        return ResponseEntity.ok(response);
    }
}
