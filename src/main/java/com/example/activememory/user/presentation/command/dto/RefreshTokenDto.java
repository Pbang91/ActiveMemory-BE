package com.example.activememory.user.presentation.command.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record RefreshTokenDto(
        @NotEmpty
        @Schema(
                description = "로그인 시 발급받은 Refresh Token",
                example = "maybe JWT",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String refreshToken
) {
}
