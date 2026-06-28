package com.liftlog.liftlog.controller;

import com.liftlog.liftlog.dto.request.SaveExerciseDto;
import com.liftlog.liftlog.service.ExcerciseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExerciseController {

    private final ExcerciseService excerciseService;

    public ExerciseController(ExcerciseService excerciseService){
        this.excerciseService = excerciseService;
    }


    @GetMapping("/exercises")
    public List<SaveExerciseDto> getAllExcercises
}
