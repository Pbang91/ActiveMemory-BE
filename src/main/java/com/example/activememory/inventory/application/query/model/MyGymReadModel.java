package com.example.activememory.inventory.application.query.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record MyGymReadModel(
        @NotNull
        @Schema(description = "MyGym id", example = "2")
        Long myGymId,

        @NotNull
        @Schema(description = "등록한 체육관 이름", example = "에이블짐")
        String name,

        @NotNull
        @Schema(description = "체육관 일반 주소", example = "서울시 압구정 비싼동")
        String address
) {
}
