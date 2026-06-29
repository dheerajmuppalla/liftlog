package com.liftlog.liftlog.service;

import com.liftlog.liftlog.dto.request.SaveExerciseDto;
import com.liftlog.liftlog.dto.response.ExerciseDto;
import com.liftlog.liftlog.entity.Exercise;
import com.liftlog.liftlog.entity.MuscleGroup;
import com.liftlog.liftlog.exception.DuplicateRecordFoundException;
import com.liftlog.liftlog.exception.RecordNotFoundException;
import com.liftlog.liftlog.repo.ExerciseRepo;
import com.liftlog.liftlog.repo.MuscleGroupRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepo exerciseRepo;
    private final MuscleGroupRepo muscleGroupRepo;
    public ExerciseService(ExerciseRepo exerciseRepo, MuscleGroupRepo muscleGroupRepo){
        this.exerciseRepo  = exerciseRepo;
        this.muscleGroupRepo = muscleGroupRepo;
    }

    public List<ExerciseDto> getAllExercise() {
        List<Exercise> exercises = exerciseRepo.findAll();
        List<ExerciseDto> result = new ArrayList<>();
        for(Exercise exercise : exercises){ //exercises
           ExerciseDto obj =  ExerciseDto.builder()
                    .id(exercise.getId())
                    .name(exercise.getName())
                    .description(exercise.getDescription())
                    .equipment(exercise.getEquipment())
                    .difficulty(exercise.getDifficulty())
                    .primaryMuscle(exercise.getPrimaryMuscleId().getName())
                    .secondaryMuscle(exercise.getSecondaryMuscleId()!=null?exercise.getSecondaryMuscleId().getName():null)
                    .videoUrl(exercise.getVideoUrl())
                    .build();

            result.add(obj);
        }

        return result;
    }



    @Transactional
    public Exercise saveExercise(SaveExerciseDto dto) {

        String name = dto.getName().trim();

        if (exerciseRepo.existsByNameIgnoreCase(name)) {
            throw new DuplicateRecordFoundException(
                    "Exercise '" + name + "' already exists."
            );
        }

        MuscleGroup primaryMuscle = muscleGroupRepo.findById(dto.getPrimaryMuscleId())
                .orElseThrow(() ->
                        new RecordNotFoundException("Primary muscle group not found."));

        MuscleGroup secondaryMuscle = null;

        if (dto.getSecondaryMuscleId() != null) {
            secondaryMuscle = muscleGroupRepo.findById(dto.getSecondaryMuscleId())
                    .orElseThrow(() ->
                            new RecordNotFoundException("Secondary muscle group not found."));
        }

        Exercise exercise = Exercise.builder()
                .name(name.toLowerCase())
                .description(dto.getDescription())
                .equipment(dto.getEquipment())
                .difficulty(dto.getDifficulty())
                .primaryMuscleId(primaryMuscle)
                .secondaryMuscleId(secondaryMuscle)
                .videoUrl(dto.getVideoUrl())
                .build();

        return exerciseRepo.save(exercise);
    }

    public ExerciseDto getExerciseById(@Positive Long id) {
        Exercise obj =  exerciseRepo.findById(id).orElseThrow(()->new RecordNotFoundException("Record with " + id + " not found"));
        return ExerciseDto.builder()
                .id(obj.getId())
                .name(obj.getName())
                .equipment(obj.getEquipment())
                .difficulty(obj.getDifficulty())
                .description(obj.getDescription())
                .videoUrl(obj.getVideoUrl())
                .primaryMuscle(obj.getPrimaryMuscleId().getName())
                .secondaryMuscle(obj.getSecondaryMuscleId()!=null?obj.getSecondaryMuscleId().getName():null)
                .build();
    }

    @Transactional
    public void deleteExerciseById( Long id) {
        if(!exerciseRepo.existsById(id)){
            throw new RecordNotFoundException("Record with " + id + " not found");
        }

        exerciseRepo.deleteById(id);
    }


    @Transactional
    public ExerciseDto updateExercise(Long id, SaveExerciseDto saveExerciseDto) {
        Exercise obj = exerciseRepo.findById(id).orElseThrow(()->
             new RecordNotFoundException("Record with "+ id + " not found")
        );

        MuscleGroup primary = muscleGroupRepo.findById(saveExerciseDto.getPrimaryMuscleId()).orElseThrow(()->new RecordNotFoundException("Primary muscle with " + saveExerciseDto.getPrimaryMuscleId() + " not there"));

        MuscleGroup secondaryMuscle = null;

        if (saveExerciseDto.getSecondaryMuscleId() != null) {
            secondaryMuscle = muscleGroupRepo.findById(saveExerciseDto.getSecondaryMuscleId())
                    .orElseThrow(() ->
                            new RecordNotFoundException("Secondary muscle group not found."));
        }

        obj.setName(saveExerciseDto.getName());
        obj.setDescription(saveExerciseDto.getDescription());
        obj.setDifficulty(saveExerciseDto.getDifficulty());
        obj.setEquipment(saveExerciseDto.getEquipment());
        obj.setPrimaryMuscleId(primary);
        obj.setSecondaryMuscleId(secondaryMuscle);
        obj.setVideoUrl(saveExerciseDto.getVideoUrl());

        Exercise data= exerciseRepo.save(obj);
        return ExerciseDto.builder().id(data.getId()).build();




    }
}
