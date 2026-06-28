package com.liftlog.liftlog.service;

import com.liftlog.liftlog.dto.MuscleGroupDto;
import com.liftlog.liftlog.entity.MuscleGroup;
import com.liftlog.liftlog.repo.MuscleGroupRepo;
import org.springframework.stereotype.Service;

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
}
