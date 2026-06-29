package com.liftlog.liftlog.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExerciseDto {

    private Long id;
    private String name;
    private String description;
    private String equipment;
    private String difficulty;
    private String primaryMuscle;
    private String secondaryMuscle;
    private String videoUrl;


}
