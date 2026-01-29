package com.edtech.course_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubTopicResponse {

    private String id;
    private String title;
    private String markdownContent;
}
