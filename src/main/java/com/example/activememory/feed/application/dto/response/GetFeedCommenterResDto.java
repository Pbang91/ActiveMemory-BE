package com.example.activememory.feed.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Objects;

public record GetFeedCommenterResDto(
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
        String imageUrl,

        @Schema(description = "commend id", example = "521",  requiredMode = Schema.RequiredMode.REQUIRED)
        Long commentId,

        @Schema(description = "comment", example = "좀 치시네요", requiredMode = Schema.RequiredMode.REQUIRED)
        String comment,

        @Schema(description = "최종 수정 시각", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDateTime updatedAt
) {
    public GetFeedCommenterResDto(String slug, String nickname, String imageUrl, Long commentId, String comment, LocalDateTime updatedAt) {
        this.slug = Objects.requireNonNull(slug);
        this.nickname = Objects.requireNonNull(nickname);
        this.imageUrl = imageUrl;
        this.commentId = Objects.requireNonNull(commentId);
        this.comment = Objects.requireNonNull(comment);
        this.updatedAt = Objects.requireNonNull(updatedAt);
    }
}
