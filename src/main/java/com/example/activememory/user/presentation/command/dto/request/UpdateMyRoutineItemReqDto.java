package com.example.activememory.user.presentation.command.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.openapitools.jackson.nullable.JsonNullable;

import java.math.BigDecimal;

public record UpdateMyRoutineItemReqDto(
        @Schema(
                description = "목표 셋트",
                example = "6",
                minimum = "1",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                implementation = Short.class
        )
        @Min(1)
        JsonNullable<Short> set,

        @Schema(
                description = "셋트별 횟수",
                example = "20",
                minimum = "1",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                implementation = Short.class
        )
        @NotNull
        @Min(1)
        JsonNullable<Short> rep,

        @Schema(
                description = "셋트별 횟수",
                example = "20",
                minimum = "0.25",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                implementation = BigDecimal.class
        )
        @DecimalMin("0.25")
        JsonNullable<BigDecimal> weight,

        @Schema(
                description = "운동별 메모",
                example = "벤치는 꼭 채워야지",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                nullable = true,
                implementation = String.class
        )
        JsonNullable<String> memo
) {
}
