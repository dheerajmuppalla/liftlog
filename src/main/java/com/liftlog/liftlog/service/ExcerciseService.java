package com.liftlog.liftlog.service;

import com.liftlog.liftlog.repo.ExerciseRepo;
import org.springframework.stereotype.Service;

@Service
public class ExcerciseService {

    private final ExerciseRepo exerciseRepo;
    public ExcerciseService(ExerciseRepo exerciseRepo){
        this.exerciseRepo  = exerciseRepo;
    }
}
