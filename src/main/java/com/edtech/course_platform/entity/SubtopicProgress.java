package com.edtech.course_platform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "subtopic_progress",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "subtopic_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubtopicProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "subtopic_id", nullable = false)
    private Subtopic subtopic;

    private Instant completedAt;
}
