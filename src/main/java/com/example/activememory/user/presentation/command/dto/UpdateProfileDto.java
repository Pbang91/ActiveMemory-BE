package com.example.activememory.user.presentation.command.dto;

import com.example.activememory.global.exception.CustomException;
import com.example.activememory.global.exception.ExceptionCode;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

public record UpdateProfileDto(
        @Schema(description = "닉네임", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        @Length(max = 30) String nickname,

        @Schema(description = "프로필 이미지 url", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        @URL String imageUrl,

        @Schema(description = "소개글", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        String bio
) {
    public UpdateProfileDto(String nickname, String imageUrl, String bio) {
        if (nickname != null && nickname.isBlank()) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETER, "닉네임은 빈값일 수 없습니다");
        }

        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.bio = bio;
    }
}
