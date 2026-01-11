package com.example.activememory.reference.application.query.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record StandardExerciseReadModel(
        @NotNull
        @Schema(description = "standardExercise id", example = "20")
        Long id,

        @NotNull
        @Schema(description = "운동명", example = "렛풀다운")
        String name,

        @NotNull
        @Schema(description = "운동 설명", example = "열심히 해야 해요")
        String description,

        @NotNull
        @Schema(description = "운동 대분류")
        BodyPartReadModel bodyPart,

        @NotNull
        @Schema(description = "운동 방법")
        ExerciseTypeReadModel exerciseType,

        @ArraySchema(
                schema = @Schema(description = "운동 방법에 타게팅되는 소분류 근유")
        )
        List<MuscleReadModel> muscles

) {
}
