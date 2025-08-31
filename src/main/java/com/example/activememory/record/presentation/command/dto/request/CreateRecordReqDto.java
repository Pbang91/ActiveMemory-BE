package com.example.activememory.record.presentation.command.dto.request;

import com.example.activememory.global.share.enums.Visibility;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;
import java.util.List;

public record CreateRecordReqDto(
        @Schema(description = "기록명", example = "이상하게 운동 잘된 날", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String name,

        @Schema(description = "기록 공개 범위", example = "PRIVATE", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        Visibility visibility,

        @Schema(
                description = "기록 자유 입력 텍스트",
                example = ".....?",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                nullable = true,
                implementation = String.class
        )
        JsonNullable<String> freeInput,

        @Schema(
                description = "실제 운동을 수행한 일자. 값을 전달하지 않는다면(key를 보내지 않거나 null로 보낼 시) today가 default",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                defaultValue = "now()"
        )
        LocalDate workoutDate,

        @ArraySchema(
                schema = @Schema(
                        description = "기록에 들어간 운동 측정 항목",
                        implementation = CreateRecordMetricReqDto.class
                ),
                arraySchema = @Schema(
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                        nullable = true
                )
        )
        JsonNullable<List<CreateRecordMetricReqDto>> metricList,

        @ArraySchema(
                schema = @Schema(
                        description = "개별 태그명",
                        example = "킹하체"
                ),
                arraySchema = @Schema(
                        description = "freeInput 영역에서 작성된 태그 리스트. 키는 생략 가능, 빈배열일 경우 무시",
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED
                ),
                minItems = 0
        )
        List<String> tagList,

        @ArraySchema(
                schema = @Schema(
                        description = "개별 Object key",
                        example = "record/image-1.jpg"
                ),
                arraySchema = @Schema(
                        description = "presigned url로 등록한 objectKey list",
                        minLength = 0,
                        maxLength = 1,
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED

                ),
                minItems = 0
        )
        @Length(max = 1)
        List<String> objectKeyList
) {
}
