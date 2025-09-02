package com.example.activememory.feed.application.dto.response;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

public record GetFeedResDto(
        @Schema(description = "record name", example = "운동 짱 잘된 날", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String name,

        @Schema(
                description = "record slug. 공유용 record unique id",
                example = "nfuqpzn",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank
        String slug,

        @Schema(
                description = "record free input text",
                example = "......ok?",
                requiredMode = Schema.RequiredMode.REQUIRED,
                nullable = true
        )
        String freeInput,

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
                schema = @Schema(description = "좋아요 회원 목록", implementation = GetFeedLikerResDto.class),
                arraySchema = @Schema(
                        requiredMode = Schema.RequiredMode.REQUIRED
                ),
                minItems = 0
        )
        List<GetFeedLikerResDto> likerList,

        @ArraySchema(
                schema = @Schema(description = "댓글 관련 정보(회원 정보 포함)", implementation = GetFeedCommenterResDto.class),
                arraySchema = @Schema(
                        requiredMode = Schema.RequiredMode.REQUIRED
                ),
                minItems = 0
        )
        List<GetFeedCommenterResDto> commentInfoList
) {
    public GetFeedResDto(
            String name,
            String slug,
            String freeInput,
            List<GetFeedMetricResDto> metricList,
            List<GetFeedLikerResDto> likerList,
            List<GetFeedCommenterResDto> commentInfoList
    ) {
        this.name = Objects.requireNonNull(name);
        this.slug = Objects.requireNonNull(slug);
        this.freeInput = freeInput;
        this.metricList = metricList;
        this.likerList = likerList;
        this.commentInfoList = commentInfoList;
    }
}
