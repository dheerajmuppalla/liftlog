package com.liftlog.liftlog.controller;

import com.liftlog.liftlog.dto.response.MuscleGroupDto;
import com.liftlog.liftlog.dto.request.SaveMuscleGroupDto;
import com.liftlog.liftlog.entity.MuscleGroup;
import com.liftlog.liftlog.response.ResponseData;
import com.liftlog.liftlog.service.MuscleGroupService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class MuscleGroupController {

    private final MuscleGroupService muscleGroupService;
    public MuscleGroupController(MuscleGroupService muscleGroupService){
        this.muscleGroupService = muscleGroupService;
    }

    @GetMapping("/muscle-groups")
    public ResponseEntity<?> getAllMuscleGroups(){
        List<MuscleGroupDto> data = muscleGroupService.getAllMuscleGroupData();
        return ResponseEntity.ok(new ResponseData<List<MuscleGroupDto>>(true,"Retrieved successfully",data));
    }


    @PostMapping("/muscle-group")
    public ResponseEntity<?> saveMuscleGroup(@Valid @RequestBody SaveMuscleGroupDto saveMuscleGroupDto){

        MuscleGroup save = muscleGroupService.saveMuscleGroup(saveMuscleGroupDto);
        return ResponseEntity.ok(new ResponseData<MuscleGroupDto>(true,"Added successfully ",MuscleGroupDto.builder().id(save.getId()).name(save.getName()).build()));
    }

    @DeleteMapping("/muscle-group/{id}")
    public ResponseEntity<?> deleteMuscleGroup( @PathVariable  @Positive(message = "id must be greater than zero") Long id){

        muscleGroupService.deleteMuscleGroup(id);
        return ResponseEntity.ok(new ResponseData<Long>(true,"Deleted successfully ",id));

    }


    @PutMapping("/muscle-group/{id}")
    public ResponseEntity<?> updateMuscleGroup(@PathVariable @Positive(message = "id must be greater than zero") Long id,
                                                   @Valid @RequestBody SaveMuscleGroupDto saveMuscleGroupDto){

       MuscleGroup record =  muscleGroupService.updateMuscleGroup(id,saveMuscleGroupDto);

        return ResponseEntity.ok(new ResponseData<MuscleGroupDto>(true,"Updated successfully",
                MuscleGroupDto.builder().id(record.getId()).name(record.getName()).build()));
    }

}
