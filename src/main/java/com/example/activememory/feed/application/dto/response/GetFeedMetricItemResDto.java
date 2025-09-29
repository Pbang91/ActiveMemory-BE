package com.example.activememory.feed.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Objects;

public record GetFeedMetricItemResDto(
        @Schema(description = "세트", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer set,

        @Schema(description = "세트 당 횟수", example = "12", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer rep,

        @Schema(description = "횟수 당 무게", example = "50.5", requiredMode = Schema.RequiredMode.REQUIRED)
        BigDecimal weight,

        @Schema(description = "세트별 메모", example = "5회쯤 쉬어버렸다", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        String memo
) {
    public GetFeedMetricItemResDto(Integer set, Integer rep, BigDecimal weight, String memo) {
        this.set = Objects.requireNonNull(set);
        this.rep = Objects.requireNonNull(rep);
        this.weight = Objects.requireNonNull(weight);
        this.memo = memo;
    }
}
