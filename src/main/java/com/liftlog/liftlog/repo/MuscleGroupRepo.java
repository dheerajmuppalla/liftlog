package com.liftlog.liftlog.repo;

import com.liftlog.liftlog.entity.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuscleGroupRepo extends JpaRepository<MuscleGroup,Long> {
}
