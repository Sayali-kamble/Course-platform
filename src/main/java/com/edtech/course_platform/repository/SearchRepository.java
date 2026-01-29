package com.edtech.course_platform.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SearchRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> search(String query) {


        String tsQuery = Arrays.stream(query.trim().split("\\s+"))
                .map(word -> word + ":*")
                .collect(Collectors.joining(" & "));

        String sql = """
            SELECT 
                c.id AS course_id,
                c.title AS course_title,
                t.title AS topic_title,
                st.id AS subtopic_id,
                st.title AS subtopic_title,
                ts_headline(
                    'english',
                    st.content,
                    to_tsquery('english', :tsQuery)
                ) AS snippet
            FROM courses c
            LEFT JOIN topics t ON t.course_id = c.id
            LEFT JOIN subtopics st ON st.topic_id = t.id
            WHERE
                (
                    setweight(to_tsvector('english', c.title), 'A') ||
                    setweight(to_tsvector('english', c.description), 'B') ||
                    setweight(to_tsvector('english', t.title), 'A') ||
                    setweight(to_tsvector('english', st.title), 'A') ||
                    setweight(to_tsvector('english', st.content), 'C')
                )
                @@ to_tsquery('english', :tsQuery)
            ORDER BY
                ts_rank(
                    setweight(to_tsvector('english', c.title), 'A') ||
                    setweight(to_tsvector('english', c.description), 'B') ||
                    setweight(to_tsvector('english', t.title), 'A') ||
                    setweight(to_tsvector('english', st.title), 'A') ||
                    setweight(to_tsvector('english', st.content), 'C'),
                    to_tsquery('english', :tsQuery)
                ) DESC
        """;

        return entityManager
                .createNativeQuery(sql)
                .setParameter("tsQuery", tsQuery)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(
                        org.hibernate.transform.AliasToEntityMapResultTransformer.INSTANCE
                )
                .getResultList();
    }
}
