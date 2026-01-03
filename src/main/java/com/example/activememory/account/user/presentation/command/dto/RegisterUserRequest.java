package com.example.activememory.account.user.presentation.command.dto;

import com.example.activememory.account.user.application.command.dto.RegisterUserCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserRequest(
        @NotBlank(message = "이메일은 필수입니다")
        @Email(message = "이메일 형식이 올바르지 않습니다")
        @Schema(description = "이메일 회원가입 시 사용할 이메일", example = "test@gmail.com")
        String email,

        @NotBlank(message = "비밀번호는 필수입니다")
        @Schema(description = "이메일 회원가입 시 사용할 비밀번호", example = "1234")
        String password,

        @NotBlank(message = "닉네임은 필수입니다")
        @Schema(description = "커뮤니티에서 사용할 닉네임", example = "3대 650", maxLength = 20)
        String nickname,

        @Schema(description = "커뮤니티에서 사용할 자기 소개", example = "음")
        String bio
) {
    public RegisterUserCommand toCommand() {
        return new RegisterUserCommand(email, password, nickname, bio);
    }
}
