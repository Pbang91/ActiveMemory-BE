package com.example.activememory.user.presentation.command.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record AuthWithKakaoTokenReqDto(
        @NotNull
        @Schema(
                description = "네이티브 카카오톡에서 발급받은 Access 토큰",
                example = "maybe jwt token style",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String accessToken,

        @Schema(
                description = "네이티브 카카오톡에서 발급받은 RefreshToken 토큰",
                example = "maybe jwt token style",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        String refreshToken
) {
}
