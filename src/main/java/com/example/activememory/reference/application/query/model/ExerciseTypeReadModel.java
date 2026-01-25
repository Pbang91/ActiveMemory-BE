package com.example.activememory.reference.application.query.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ExerciseTypeReadModel(
        @NotNull
        @Schema(description = "종류 이름", example = "BARBELL")
        String name,

        @NotNull
        @Schema(description = "종류 한글 이름", example = "바벨")
        String koName
) {
}
