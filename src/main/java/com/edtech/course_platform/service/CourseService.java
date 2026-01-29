package com.edtech.course_platform.service;

import com.edtech.course_platform.dto.CourseDetailResponse;
import com.edtech.course_platform.dto.CourseResponse;

import java.util.List;

public interface CourseService {

    List<CourseResponse> getAllCourses();

    CourseDetailResponse getCourseById(String courseId);
}
