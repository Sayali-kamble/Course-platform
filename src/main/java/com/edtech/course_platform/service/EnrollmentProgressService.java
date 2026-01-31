package com.edtech.course_platform.service;

import com.edtech.course_platform.dto.EnrollmentProgressResponse;
import com.edtech.course_platform.entity.User;

public interface EnrollmentProgressService {
    public EnrollmentProgressResponse getProgress(
            Long enrollmentId,
            User user
    );
}
