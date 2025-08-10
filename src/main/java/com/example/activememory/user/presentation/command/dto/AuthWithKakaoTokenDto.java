package com.example.activememory.user.presentation.command.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record AuthWithKakaoTokenDto(
        @NotNull
        @Schema(
                description = "네이티브 환경(카카오톡 등)에서 발급받은 토큰",
                example = "maybe jwt token style",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String authToken
) {
}
