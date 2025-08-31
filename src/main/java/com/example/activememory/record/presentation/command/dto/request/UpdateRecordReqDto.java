package com.example.activememory.record.presentation.command.dto.request;

import com.example.activememory.global.share.enums.Visibility;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;
import org.openapitools.jackson.nullable.JsonNullable;

import java.time.LocalDate;
import java.util.List;

public record UpdateRecordReqDto(
        @Schema(
                description = "기록명",
                example = "이상하게 운동 안된 날",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                implementation = String.class
        )
        JsonNullable<String> name,

        @Schema(
                description = "기록 자유 입력 텍스트",
                example = ".....수정?",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                nullable = true,
                implementation = String.class
        )
        JsonNullable<String> freeInput,

        @Schema(
                description = "기록 공개 범위",
                example = "PUBLIC",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        JsonNullable<Visibility> visibility,

        @Schema(
                description = "실제 운동을 수행한 일자. 업데이트에서 null은 허용하지 않습니다",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED
        )
        JsonNullable<LocalDate> workoutDate,

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
                schema = @Schema(implementation = String.class),
                arraySchema = @Schema(
                        description = "freeInput 영역에서 작성된 태그 리스트. empty string이나 null 전달 시 전체 삭제(주의 필요)",
                        example = "[\"킹하체\"]",
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                        nullable = true
                )
        )
        JsonNullable<List<String>> tagList,

        @ArraySchema(
                schema = @Schema(implementation = String.class),
                arraySchema = @Schema(
                        description = "presigned url로 등록한 objectKey list. empty string이나 null 전달 시 전체 삭제(주의 필요)",
                        example = "[\"record/image-1.jpg\"]",
                        maxLength = 1,
                        requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                        nullable = true
                )
        )
        @Length(max = 1)
        JsonNullable<List<String>> objectKeyList
) {
}
