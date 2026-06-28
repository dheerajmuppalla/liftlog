package com.liftlog.liftlog.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "workout_sessions",indexes = {
        @Index(name = "idx_session_user",columnList = "user_id"),
        @Index(name = "idx_session_date",columnList = "started_at")
})
public class WorkoutSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private WorkoutTemplate workoutTemplate;

    @Column(name = "started_at",nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;


    @Column(name = "notes")
    private String notes;



    @Column(name = "is_completed",columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isCompleted;


    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

}
