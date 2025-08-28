package com.example.activememory.user.application.query.dto.response;

import com.example.activememory.global.exception.CustomException;
import com.example.activememory.global.exception.ExceptionCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record ProfileResDto(
        @Schema(
                description = "서비스 내 닉네임. 설정을 따로 하지 않았다면 임의의 문자열",
                example = "dnaiql12",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotEmpty String nickname,

        @Schema(description = "프로필 이미지 주소", example = "https://....", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String imageUrl,

        @Schema(description = "자기 소개글", example = "hi, i'm ...", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String bio
) {
    public ProfileResDto(String nickname, String imageUrl, String bio) {
        if (nickname == null || nickname.isEmpty()) {
            throw new CustomException(ExceptionCode.INVALID_PARAMETER, "뭔가 잘못됨");
        }

        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.bio = bio;
    }
}
