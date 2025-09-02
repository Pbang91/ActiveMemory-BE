package com.example.activememory.feed.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

public record GetFeedLikerResDto(
        @Schema(description = "user slug", example = "qnizq12nakz", requiredMode = Schema.RequiredMode.REQUIRED)
        String slug,

        @Schema(description = "user nickname", example = "5대200", requiredMode = Schema.RequiredMode.REQUIRED)
        String nickname,

        @Schema(
                description = "user profiler image url",
                example = "https://.....image.com",
                requiredMode = Schema.RequiredMode.REQUIRED,
                nullable = true
        )
        String imageUrl
) {
    public GetFeedLikerResDto(String slug, String nickname, String imageUrl) {
        this.slug = Objects.requireNonNull(slug);
        this.nickname = Objects.requireNonNull(nickname);
        this.imageUrl = imageUrl;
    }
}
