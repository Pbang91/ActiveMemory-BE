package com.example.activememory.account.user.application.query.model;

import com.example.activememory.global.enums.AuthType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UserMeReadModel(
        @NotNull
        @Schema(description = "user id", example = "2")
        Long id,

        @Schema(description = "user email", example = "test@test.com")
        String email,

        @NotBlank
        @Schema(description = "user nickname", example = "최고 무게왕")
        String nickname,

        @Schema(description = "user bio(description)", example = "무게 잘쳐요")
        String bio,

        @NotNull
        @Schema(description = "가입 형식", example = "EMAIL")
        AuthType authType,

        @NotNull
        @Schema(description = "가입일", example = "2026-01-01T10:00:20", type = "string")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt
) {
}
