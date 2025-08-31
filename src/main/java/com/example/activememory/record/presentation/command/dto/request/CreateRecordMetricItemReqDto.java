package com.example.activememory.record.presentation.command.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateRecordMetricItemReqDto(
        @Schema(
                description = "set num",
                example = "1",
                minimum = "1",
                maximum = "32767",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        @Min(1)
        @Max(32767)
        Integer set,

        @Schema(
                description = "해당 세트에 수행한 횟수",
                example = "10",
                minimum = "1",
                maximum = "32767",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        @Min(1)
        @Max(32767)
        Integer rep,

        @Schema(
                description = "회당 무게",
                example = "50.5",
                minimum = "0.25",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull
        @DecimalMin("0.25")
        BigDecimal weight,

        @Schema(
                description = "세트당 메모",
                example = "5회 쯤 3분 쉼",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                nullable = true
        )
        String memo
) {
}
