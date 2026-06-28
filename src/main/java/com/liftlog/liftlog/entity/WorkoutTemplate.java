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
@Table(name = "workout_templates",indexes = {
        @Index(name = "idx_template_user",columnList = "user_id")
})
public class WorkoutTemplate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User parent;


    @Column(name = "name",length = 150,nullable = false)
    private String name;


    @Column(name = "notes")
    private String notes;


    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
}
