package com.example.activememory.account.auth.presentation.command.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record AuthTokenResponse(
        @NotNull
        @Schema(description = "Auth AccessToken")
        String accessToken,

        @NotNull
        @Schema(description = "Auth RefreshToken")
        String refreshToken
) {
}
