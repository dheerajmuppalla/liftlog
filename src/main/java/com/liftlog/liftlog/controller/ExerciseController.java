package com.liftlog.liftlog.controller;

import com.liftlog.liftlog.dto.request.SaveExerciseDto;
import com.liftlog.liftlog.dto.response.ExerciseDto;
import com.liftlog.liftlog.entity.Exercise;
import com.liftlog.liftlog.response.ResponseData;
import com.liftlog.liftlog.service.ExerciseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService){
        this.exerciseService = exerciseService;
    }


    @GetMapping("/exercises")
    public ResponseEntity<?> getAllExcercises(){
        List<ExerciseDto> exercises = exerciseService.getAllExercise();

        return ResponseEntity.ok(new ResponseData<List<ExerciseDto>>(true,"All exercises",exercises));

    }


    @PostMapping("/exercises")
    public ResponseEntity<?> saveExercise(@Valid @RequestBody SaveExerciseDto saveExerciseDto){

       Exercise exercise =  exerciseService.saveExercise(saveExerciseDto);
       return ResponseEntity.ok(new ResponseData<Long>(true,"Added",exercise.getId()));
    }


    @GetMapping("/exercises/{id}")
    public ResponseEntity<?> getExerciseById(@PathVariable @Positive Long  id){

        ExerciseDto exercise = exerciseService.getExerciseById(id);
        return ResponseEntity.ok(new ResponseData<ExerciseDto>(true,"Exercise with id "+id,exercise));
    }

    @DeleteMapping("/exercises/{id}")
    public ResponseEntity<?> deleteExerciseById(@PathVariable @Positive Long id){
        exerciseService.deleteExerciseById(id);
        return ResponseEntity.ok(new ResponseData<Long>(true,"Deleted record with id " + id +" ",id));
    }


    @PutMapping("/exercises/{id}")
    public ResponseEntity<?> updateExerciseById(@PathVariable @Positive Long id,@Valid @RequestBody SaveExerciseDto saveExerciseDto){
        ExerciseDto exerciseDto =
                exerciseService.updateExercise(id,saveExerciseDto);

        return ResponseEntity.ok(new ResponseData<Long>(true,"Updated Exercise",exerciseDto.getId()));
    }

}
