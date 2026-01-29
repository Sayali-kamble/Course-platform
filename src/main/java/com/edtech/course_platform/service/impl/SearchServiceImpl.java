package com.edtech.course_platform.service.impl;

import com.edtech.course_platform.dto.SearchCourseResult;
import com.edtech.course_platform.dto.SearchMatchResponse;
import com.edtech.course_platform.dto.SearchResponse;
import com.edtech.course_platform.repository.SearchRepository;
import com.edtech.course_platform.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;

    @Override
    public SearchResponse search(String query) {

        List<Map<String, Object>> rows = searchRepository.search(query);

        Map<String, SearchCourseResult> courseMap = new LinkedHashMap<>();

        for (Map<String, Object> row : rows) {

            String courseId = (String) row.get("course_id");
            String courseTitle = (String) row.get("course_title");

            courseMap.putIfAbsent(
                    courseId,
                    new SearchCourseResult(courseId, courseTitle, new ArrayList<>())
            );

            SearchMatchResponse match = new SearchMatchResponse(
                    "subtopic",
                    (String) row.get("topic_title"),
                    (String) row.get("subtopic_id"),
                    (String) row.get("subtopic_title"),
                    (String) row.get("snippet")
            );

            courseMap.get(courseId).getMatches().add(match);
        }

        return new SearchResponse(query, new ArrayList<>(courseMap.values()));
    }
}
