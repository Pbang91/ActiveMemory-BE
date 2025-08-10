package com.example.activememory.user.presentation.command.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CallBackKakaoAccountAuthenticationDto(
        @Schema(description = "kakao accessToken을 얻기 위한 임시 코드", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        String code,

        @Schema(description = "error 발생 시 코드", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        String error,

        @Schema(description = "error 발생 시 설명", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        String error_description,

        @Schema(description = "state", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        String state
) {
}
