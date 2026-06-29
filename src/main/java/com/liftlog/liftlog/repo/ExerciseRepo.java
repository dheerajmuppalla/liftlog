package com.liftlog.liftlog.repo;

import com.liftlog.liftlog.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepo extends JpaRepository<Exercise,Long> {

    boolean existsByNameIgnoreCase(String name);
}
