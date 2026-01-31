package com.edtech.course_platform.controller;

import com.edtech.course_platform.dto.EnrollmentProgressResponse;
import com.edtech.course_platform.entity.User;
import com.edtech.course_platform.exception.UserNotFoundException;
import com.edtech.course_platform.repository.UserRepository;
import com.edtech.course_platform.service.EnrollmentProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentProgressController {

    private final EnrollmentProgressService progressService;
    private final UserRepository userRepository;

    @GetMapping("/{enrollmentId}/progress")
    public EnrollmentProgressResponse getProgress(
            @PathVariable Long enrollmentId
    ) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return progressService.getProgress(enrollmentId, user);
    }
}

