package com.example.activememory.account.auth.presentation.command.dto;

import com.example.activememory.account.auth.application.command.dto.LoginCommand;
import com.example.activememory.account.auth.domain.enums.AuthType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull
        @Schema(description = "로그인 진행하려는 종류", example = "EMAIL")
        AuthType authType,

        @Schema(description = "이메일 로그인 시 이메일", example = "test@gmail.com", nullable = true)
        String email,

        @Schema(description = "이메일 로그인 시 비밀번호", example = "1234", nullable = true)
        String password,

        @Schema(description = "OAuth 로그인 시 토큰", example = "ebearenjkajnfajdsknlf", nullable = true)
        String token
) {
    public LoginCommand toCommand() {
        return new LoginCommand(
                authType,
                email,
                password,
                token
        );
    }
}
