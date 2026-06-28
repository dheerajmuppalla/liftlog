package com.liftlog.liftlog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveExerciseDto {

    @NotBlank(message = "Exercise name is required")
    @Size(max = 150)
    private String name;

    @Size(max = 1000)
    private String description;

    @Size(max = 50)
    private String equipment;

    private String difficulty;

    @NotNull
    @Positive
    private Long primaryMuscleId;

    @Positive
    private Long secondaryMuscleId;

    @Size(max = 500)
    @URL
    private String videoUrl;
}