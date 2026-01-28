package com.edtech.course_platform.dto;

import lombok.Data;
import java.util.List;

@Data
public class CourseSeedWrapper {
    private List<CourseSeedDto> courses;
}
