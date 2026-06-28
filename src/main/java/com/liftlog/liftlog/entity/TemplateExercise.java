package com.liftlog.liftlog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "template_exercises",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"template_id", "exercise_order"})
        },indexes = {
        @Index(name = "idx_template_exercise",columnList = "template_id")
})
public class TemplateExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id",nullable = false)
    private WorkoutTemplate template;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id",nullable = false)
    private Exercise exercise;

    @Column(name = "exercise_order",nullable = false)
    private int exerciseOrder;



    @Column(name = "target_sets")
    private int targetSets;


    @Column(name = "target_reps",length = 20)
    private String targetReps;

    @Column(name = "target_weight",precision = 6,scale = 2)
    private BigDecimal targetWeight;


    @Column(name = "rest_seconds")
    private int restSeconds;

}
