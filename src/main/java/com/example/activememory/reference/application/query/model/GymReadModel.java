package com.example.activememory.reference.application.query.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record GymReadModel(
        @NotNull
        @Schema(description = "Gym Provider Id", example = "kakao:123456")
        String providerId,

        @NotNull
        @Schema(description = "체육관 이름", example = "레몬핏")
        String name,

        @NotNull
        @Schema(description = "체육관 일반 주소", example = "서울시 노원구 헬스헬스건물")
        String address,

        @NotNull
        @Schema(description = "지도상 위도", example = "127.30")
        String latitude,

        @NotNull
        @Schema(description = "지도상 경도", example = "36.555")
        String longitude
) {
}
