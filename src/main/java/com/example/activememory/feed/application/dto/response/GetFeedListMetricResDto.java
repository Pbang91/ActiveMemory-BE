package com.example.activememory.feed.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public record GetFeedListMetricResDto(
        @Schema(description = "Exercise id", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        UUID exerciseId,

        @Schema(description = "Exercise name", example = "벨트 스쿼트", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String exerciseName,

        @Schema(description = "display order", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        Integer displayOrder
) {
    public GetFeedListMetricResDto(UUID exerciseId, String exerciseName, Integer displayOrder) {
        this.exerciseId = Objects.requireNonNull(exerciseId);
        this.exerciseName = Objects.requireNonNull(exerciseName);
        this.displayOrder = Objects.requireNonNull(displayOrder);
    }
}
