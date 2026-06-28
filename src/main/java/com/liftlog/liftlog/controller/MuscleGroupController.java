package com.liftlog.liftlog.controller;

import com.liftlog.liftlog.dto.MuscleGroupDto;
import com.liftlog.liftlog.response.ResponseData;
import com.liftlog.liftlog.service.MuscleGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
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
}
