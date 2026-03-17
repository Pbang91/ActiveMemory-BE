package com.example.activememory.reference.application.query.model;

import com.example.activememory.reference.domain.exercise.enums.MuscleRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record StandardExerciseMuscleReadModel(
        @NotNull
        @Schema(description = "muscle id", example = "20")
        Long id,

        @NotNull
        @Schema(description = "근육명", example = "대흉근")
        String name,

        @NotNull
        @Schema(description = "주동근 or 협력근", example = "PRIMARY")
        MuscleRole role
) {
}
