package com.liftlog.liftlog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "exercise_sets",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "session_exercise_id",
                        "set_number"
                })
        },
        indexes = {
                @Index(
                        name = "idx_sets_session",
                        columnList = "session_exercise_id"
                )
        }
)
@Check(constraints = """
    weight >= 0 AND
    reps >= 0 AND
    duration_seconds >= 0 AND
    distance_meters >= 0
    """)
public class ExerciseSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_exercise_id", nullable = false)
    private SessionExercise sessionExercise;

    @Column(name = "set_number", nullable = false)
    private int setNumber;

    @Column(name = "weight", precision = 6, scale = 2)
    private BigDecimal weight;

    @Column(name = "reps")
    private Integer reps;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @Column(name = "distance_meters", precision = 8, scale = 2)
    private BigDecimal distanceMeters;

    @Column(name = "rir")
    private Integer rir;

    @Column(name = "rpe", precision = 3, scale = 1)
    private BigDecimal rpe;

    @Column(name = "is_warmup")
    private boolean warmUp;

    @Column(name = "completed")
    private boolean completed;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}