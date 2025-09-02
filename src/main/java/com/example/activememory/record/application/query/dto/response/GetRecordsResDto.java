package com.example.activememory.record.application.query.dto.response;

import com.example.activememory.global.share.enums.Visibility;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

public record GetRecordsResDto(
        @Schema(description = "record id", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        Long id,

        @Schema(description = "record name", example = "운동 짱 잘된 날", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String name,

        @Schema(
            description = "record slug. 공유용 record unique id. visibility가 PUBLIC이어야 전달됩니다",
            example = "nfuqpzn",
            requiredMode = Schema.RequiredMode.REQUIRED,
            nullable = true
        )
        String slug,

        @Schema(
            description = "record free input text",
            example = "......ok?",
            requiredMode = Schema.RequiredMode.REQUIRED,
            nullable = true
        )
        String freeInput,

        @Schema(description = "공개 범위", example = "PUBLIC", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        Visibility visibility,

        @ArraySchema(
                schema = @Schema(description = "운동 그룹 정보", implementation = GetRecordsMetricResDto.class),
                arraySchema = @Schema(
                        requiredMode = Schema.RequiredMode.REQUIRED,
                        nullable = true
                ),
                minItems = 0
        )
        List<GetRecordsMetricResDto> metricList,

        @Schema(
                description = "기록에 등록된 댓글 수",
                example = "2",
                requiredMode = Schema.RequiredMode.REQUIRED,
                defaultValue = "0"
        )
        long commentCount,

        @Schema(
                description = "기록에 등록된 좋아요 수",
                example = "150",
                requiredMode = Schema.RequiredMode.REQUIRED,
                defaultValue = "0"
        )
        long likeCount
) {
    public GetRecordsResDto(
            Long id,
            String name,
            String slug,
            String freeInput,
            Visibility visibility,
            List<GetRecordsMetricResDto> metricList,
            long commentCount,
            long likeCount
    ) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.slug = slug;
        this.freeInput = freeInput;
        this.visibility = Objects.requireNonNull(visibility);
        this.metricList = metricList;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
    }
}
