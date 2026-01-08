package com.example.activememory.account.user.presentation.command.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record RegisterUserResponse(
        @NotNull
        @Schema(description = "가입한 사용자 id", example = "20")
        Long userId
) {
}
