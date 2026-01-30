package com.edtech.course_platform.controller;

import com.edtech.course_platform.dto.EnrollmentResponse;
import com.edtech.course_platform.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseEnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<EnrollmentResponse> enrollCourse(
            @PathVariable String courseId,
            Authentication authentication
    ) {
        if (authentication == null) {
            throw new RuntimeException("Unauthorized");
        }

        String email = authentication.getName();

        EnrollmentResponse response = enrollmentService.enroll(courseId, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

