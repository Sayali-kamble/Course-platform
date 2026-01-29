package com.edtech.course_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchCourseResult {

    private String courseId;
    private String courseTitle;
    private List<SearchMatchResponse> matches;
}
