package com.edtech.course_platform.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String courseId) {
        super("Course with id '" + courseId + "' does not exist");
    }
}
