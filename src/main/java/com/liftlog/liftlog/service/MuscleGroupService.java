package com.liftlog.liftlog.service;

import com.liftlog.liftlog.dto.response.MuscleGroupDto;
import com.liftlog.liftlog.dto.request.SaveMuscleGroupDto;
import com.liftlog.liftlog.entity.MuscleGroup;
import com.liftlog.liftlog.exception.DuplicateRecordFoundException;
import com.liftlog.liftlog.exception.RecordNotFoundException;
import com.liftlog.liftlog.repo.MuscleGroupRepo;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MuscleGroupService {

    private final MuscleGroupRepo muscleGroupRepo;
    public MuscleGroupService(MuscleGroupRepo muscleGroupRepo){
        this.muscleGroupRepo = muscleGroupRepo;
    }


    public List<MuscleGroupDto> getAllMuscleGroupData() {
       List<MuscleGroup> muscleGroups =  muscleGroupRepo.findAll();
       List<MuscleGroupDto> result = new ArrayList<>();
       for (MuscleGroup muscleGroup: muscleGroups){
           MuscleGroupDto row = MuscleGroupDto.builder()
                   .id(muscleGroup.getId())
                   .name(muscleGroup.getName())
                   .build();

           result.add(row);
       }
        return result;
    }

    public MuscleGroup saveMuscleGroup(SaveMuscleGroupDto saveMuscleGroupDto) {
        String name = saveMuscleGroupDto.getName().toLowerCase();
        boolean exists = muscleGroupRepo.existsByName(name);
        if(exists){
            throw new DuplicateRecordFoundException(
                    "Muscle group '" + name + "' already exists."
            );        }

        MuscleGroup muscleGroup = MuscleGroup.builder().name(name).build();
        return muscleGroupRepo.save(muscleGroup);


    }

    public void deleteMuscleGroup(Long id) {

        MuscleGroup record = muscleGroupRepo.findById(id).orElseThrow(()->new RecordNotFoundException("Record with " + id + " not found"));
        muscleGroupRepo.delete(record);
    }

    public MuscleGroup updateMuscleGroup( Long id, SaveMuscleGroupDto saveMuscleGroupDto) {

        String name = saveMuscleGroupDto.getName().toLowerCase();
        MuscleGroup muscleGroup = muscleGroupRepo.findById(id).orElseThrow(()->new RecordNotFoundException("Record with " + id + " not found"));
        muscleGroup.setName(name);
        return muscleGroupRepo.save(muscleGroup);
    }
}
