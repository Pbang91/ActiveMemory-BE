package com.example.activememory.feed.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Objects;

public record GetFeedCommentResDto(
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

        @Schema(description = "등록 시각", requiredMode = Schema.RequiredMode.REQUIRED)
        LocalDateTime createdAt,

        @Schema(description = "조회하는 사용자 댓글 여부", requiredMode = Schema.RequiredMode.REQUIRED, defaultValue = "false", example = "false")
        boolean isMine

) {
    public GetFeedCommentResDto(
            String slug,
            String nickname,
            String imageUrl,
            Long commentId,
            String comment,
            LocalDateTime createdAt,
            boolean isMine
    ) {
        this.slug = Objects.requireNonNull(slug);
        this.nickname = Objects.requireNonNull(nickname);
        this.imageUrl = imageUrl;
        this.commentId = Objects.requireNonNull(commentId);
        this.comment = Objects.requireNonNull(comment);
        this.createdAt = Objects.requireNonNull(createdAt);
        this.isMine = isMine;
    }
}
