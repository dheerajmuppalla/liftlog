package com.liftlog.liftlog.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users",indexes = {
        @Index(name = "idx_users_email",columnList = "email")
})
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name",length = 100,nullable = false)
    private String fullName;

    @Column(name = "email", length = 255, nullable = false,unique = true)
    private String email;


    @Column(name = "password_hash",length = 255,nullable = false)
    private String passwordHash;

    @Column(name = "gender",length = 20)
    private String gender;

    @Column(name = "height_cm",precision = 5,scale = 2)
    private BigDecimal heightCm;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

}
