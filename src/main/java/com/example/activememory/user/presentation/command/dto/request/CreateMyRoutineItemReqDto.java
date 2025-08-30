package com.example.activememory.user.presentation.command.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateMyRoutineItemReqDto(
        @Schema(description = "운동 종목 고유 id", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        UUID exerciseId,

        @Schema(
                description = "목표 셋트",
                example = "6",
                minimum = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        @Min(1)
        Short set,

        @Schema(
                description = "셋트별 횟수",
                example = "20",
                minimum = "1",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        @Min(1)
        Short rep,

        @Schema(
                description = "셋트별 횟수",
                example = "20",
                minimum = "0.25",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        @DecimalMin("0.25")
        BigDecimal weight,

        @Schema(description = "운동별 메모", example = "벤치는 꼭 채워야지", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        String memo
) {
}
