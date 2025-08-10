package com.example.activememory.user.application.command.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthTokenResDto(
        @Schema(description = "accessToken", example = "maybe JWT", requiredMode = Schema.RequiredMode.REQUIRED)
        String accessToken,
        @Schema(description = "refreshToken", example = "maybe JWT", requiredMode = Schema.RequiredMode.REQUIRED)
        String refreshToken
) {
}
