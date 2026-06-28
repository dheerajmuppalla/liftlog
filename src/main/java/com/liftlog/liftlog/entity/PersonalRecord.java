package com.liftlog.liftlog.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "personal_records",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id","exercise_id"})
},indexes = {
        @Index(name = "idx_pr_user",columnList = "user_id")
})
public class PersonalRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id",nullable = false)
    private Exercise exercise;

    @Column(name = "weight",precision = 6,scale = 2)
    private BigDecimal weight;

    @Column(name = "reps")
    private int reps;

    @Column(name = "estimated_one_rm",precision = 6,scale = 2)
    private BigDecimal estimatedOneRm;


    @Column(name = "achieved_at")
    private LocalDateTime achievedAt;


}
