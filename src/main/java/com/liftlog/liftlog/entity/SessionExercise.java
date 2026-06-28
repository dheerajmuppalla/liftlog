package com.liftlog.liftlog.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "session_exercises",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"session_id", "exercise_order"})
        },
        indexes = {
                @Index(name = "idx_session_exercise", columnList = "session_id")
        }
)
public class SessionExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private WorkoutSession session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "exercise_order", nullable = false)
    private int exerciseOrder;

    @Column(name = "notes")
    private String notes;

}