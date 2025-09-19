package com.example.activememory.user.application.command.port.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record GetUserInformationResDto(
        @NotBlank
        @Schema(description = "OAuth id", requiredMode = Schema.RequiredMode.REQUIRED)
        String oauthId
) {
}
