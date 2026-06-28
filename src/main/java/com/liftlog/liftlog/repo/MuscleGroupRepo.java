package com.liftlog.liftlog.repo;

import com.liftlog.liftlog.entity.MuscleGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscleGroupRepo extends JpaRepository<MuscleGroup,Long> {
    MuscleGroup findByName(String name);

    boolean existsByName(String name);
}
