package com.example.activememory.feed.application.dto.response;

import com.example.activememory.record.application.query.dto.response.GetRecordsMetricResDto;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

public record GetFeedListResDto(
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
                description = "기록 설명",
                example = "......ok?",
                requiredMode = Schema.RequiredMode.REQUIRED,
                nullable = true
        )
        String freeInput,

        @ArraySchema(
                schema = @Schema(description = "운동 그룹 정보", implementation = GetFeedListMetricResDto.class),
                arraySchema = @Schema(
                        requiredMode = Schema.RequiredMode.REQUIRED,
                        nullable = true
                ),
                minItems = 0
        )
        List<GetFeedListMetricResDto> metricList,

        @Schema(
                description = "피드에 등록된 댓글 수",
                example = "2",
                requiredMode = Schema.RequiredMode.REQUIRED,
                defaultValue = "0"
        )
        long commentCount,

        @Schema(
                description = "피드에 등록된 좋아요 수",
                example = "150",
                requiredMode = Schema.RequiredMode.REQUIRED,
                defaultValue = "0"
        )
        long likeCount
) {
    public GetFeedListResDto(
            String name,
            String slug,
            String freeInput,
            List<GetFeedListMetricResDto> metricList,
            long commentCount,
            long likeCount
    ) {
        this.name = Objects.requireNonNull(name);
        this.slug = Objects.requireNonNull(slug);
        this.freeInput = freeInput;
        this.metricList = metricList;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
    }
}
