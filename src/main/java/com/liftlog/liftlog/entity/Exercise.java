package com.liftlog.liftlog.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "exercises",indexes = {
        @Index(name = "idx_exercise_name",columnList = "name"),
        @Index(name = "idx_exercise_primary_muscle",columnList = "primary_muscle_id")
})
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name",length = 150,nullable = false,unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "equipment",length = 50)
    private String equipment;


    @Column(name = "difficulty",length = 30)
    private String difficulty;

    @Column(name = "video_url",length = 500)
    private String videoUrl;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_muscle_id",nullable = false)
    private MuscleGroup primaryMuscleId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secondary_muscle_id",nullable = false)
    private MuscleGroup secondaryMuscleId;

}
