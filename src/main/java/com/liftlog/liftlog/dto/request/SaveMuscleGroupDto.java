package com.liftlog.liftlog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class SaveMuscleGroupDto {

    @NotBlank(message = "name is required")
    @Size(max = 50)
    private String name;
}
