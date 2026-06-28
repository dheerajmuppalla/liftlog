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
@Table(
        name = "body_weight_logs",
        indexes = {
                @Index(name = "idx_weight_user", columnList = "user_id"),
                @Index(name = "idx_weight_date", columnList = "logged_at")
        }
)
public class BodyWeightLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "weight", nullable = false, precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(name = "logged_at", nullable = false)
    private LocalDateTime loggedAt;
}