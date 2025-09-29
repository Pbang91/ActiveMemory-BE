package com.example.activememory.feed.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record AuthorProfileResDto(
        @Schema(
                description = "서비스 내 닉네임. 설정을 따로 하지 않았다면 임의의 문자열",
                example = "dnaiql12",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank String nickname,

        @Schema(description = "프로필 이미지 주소", example = "https://....", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String imageUrl,

        @Schema(
                description = "user slug. 공유용 user unique id",
                example = "nfuqpzn",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank
        String slug
) {
}
