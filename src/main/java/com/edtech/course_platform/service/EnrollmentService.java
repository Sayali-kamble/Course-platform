package com.edtech.course_platform.service;

import com.edtech.course_platform.dto.EnrollmentResponse;

public interface EnrollmentService {
    public EnrollmentResponse enroll(String courseId, String userEmail);
}
