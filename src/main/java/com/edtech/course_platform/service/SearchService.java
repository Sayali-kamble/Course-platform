package com.edtech.course_platform.service;

import com.edtech.course_platform.dto.SearchResponse;

public interface SearchService {
    SearchResponse search(String query);
}
