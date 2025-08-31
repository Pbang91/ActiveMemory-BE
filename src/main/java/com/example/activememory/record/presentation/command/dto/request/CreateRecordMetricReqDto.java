package com.example.activememory.record.presentation.command.dto.request;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CreateRecordMetricReqDto(
        @Schema(description = "운동 고유 id", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        UUID exerciseId,

        @Schema(description = "화면에서 표현되는 운동(그룹) 순서", example = "2" ,minimum = "1", maximum = "32767")
        @NotNull
        @Min(1)
        @Max(32767)
        Integer displayOrder,

        @ArraySchema(
                schema = @Schema(
                        description = "셋트 정보",
                        implementation = CreateRecordMetricItemReqDto.class
                ),
                arraySchema = @Schema(
                        requiredMode = Schema.RequiredMode.REQUIRED
                )
        )
        @NotEmpty
        List<@Valid CreateRecordMetricItemReqDto> itemList
) {
}
