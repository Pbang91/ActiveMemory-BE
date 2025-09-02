package com.example.activememory.feed.application.dto.response;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record GetFeedMetricResDto(
        @Schema(description = "Exercise id", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        UUID exerciseId,

        @Schema(description = "Exercise name", example = "벨트 스쿼트", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String exerciseName,

        @Schema(description = "display order", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        Integer displayOrder,

        @ArraySchema(
                schema = @Schema(
                        description = "해당 운동의 세트별 정보",
                        implementation = GetFeedMetricItemResDto.class
                ),
                arraySchema = @Schema(
                        requiredMode = Schema.RequiredMode.REQUIRED
                )
        )
        @NotEmpty
        List<GetFeedMetricItemResDto> setList
) {
    public GetFeedMetricResDto(
            UUID exerciseId,
            String exerciseName,
            Integer displayOrder,
            List<GetFeedMetricItemResDto> setList
    ) {
        this.exerciseId = Objects.requireNonNull(exerciseId);
        this.exerciseName = Objects.requireNonNull(exerciseName);
        this.displayOrder = Objects.requireNonNull(displayOrder);

        if (setList == null || setList.isEmpty()) {
            throw new NullPointerException("뭔가 잘못");
        }

        this.setList = setList;
    }
}
