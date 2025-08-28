package com.example.activememory.user.presentation.command.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record AuthWithAppleCredentialReqDto(
        @NotNull
        @Schema(
                description = "iOS or MacOS 등에서 발급받은 credentials",
                example = "maybe String style",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String credentials
) {
}
