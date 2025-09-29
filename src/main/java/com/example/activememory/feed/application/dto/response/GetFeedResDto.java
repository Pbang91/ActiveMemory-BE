package com.example.activememory.feed.application.dto.response;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public record GetFeedResDto(
        @Schema(
                description = "record slug. 공유용 record unique id",
                example = "nfuqpzn",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank
        String slug,

        @Schema(
                description = "기록 설명",
                example = "......ok?",
                requiredMode = Schema.RequiredMode.REQUIRED,
                nullable = true
        )
        String freeInput,

        @Schema(
                description = "피드 작성자 프로필 정보",
                implementation = AuthorProfileResDto.class,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        AuthorProfileResDto authorProfile,

        @ArraySchema(
                schema = @Schema(description = "운동 그룹 정보", implementation = GetFeedMetricResDto.class),
                arraySchema = @Schema(
                        requiredMode = Schema.RequiredMode.REQUIRED,
                        nullable = true
                ),
                minItems = 0
        )
        List<GetFeedMetricResDto> metricList,

        @ArraySchema(
                schema = @Schema(
                        description = "피드에 등록된 댓글 정보",
                        implementation = GetFeedCommentResDto.class
                ),
                arraySchema = @Schema(
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED
                )
        )
        List<GetFeedCommentResDto> commentList,

        @ArraySchema(
                schema = @Schema(
                      description = "피드에 좋아요를 누른 사람의 정보",
                      implementation = GetFeedLikerResDto.class
                ),
                arraySchema = @Schema(
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED
                )
        )
        List<GetFeedLikerResDto> likerList,

        @Schema(
                description = "기록 작성일",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        LocalDateTime createdAt,

        @Schema(
                description = "기록에 포함된 사진 url",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                nullable = true
        )
        String imageUrl,

        @Schema(
                description = "현재 사용자의 피드 좋아요 여부",
                requiredMode = Schema.RequiredMode.REQUIRED,
                defaultValue = "false",
                example = "false"
        )
        boolean likedFeed
) {
    public GetFeedResDto(
            String slug,
            String freeInput,
            AuthorProfileResDto authorProfile,
            List<GetFeedMetricResDto> metricList,
            List<GetFeedCommentResDto> commentList,
            List<GetFeedLikerResDto> likerList,
            LocalDateTime createdAt,
            String imageUrl,
            boolean likedFeed
    ) {
        this.slug = Objects.requireNonNull(slug);
        this.authorProfile = Objects.requireNonNull(authorProfile);
        this.freeInput = freeInput;
        this.metricList = metricList;
        this.commentList = commentList;
        this.likerList = likerList;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.likedFeed = likedFeed;
    }
}
