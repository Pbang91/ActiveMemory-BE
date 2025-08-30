package com.example.activememory.user.presentation.command.dto.request;

import com.example.activememory.global.share.enums.Visibility;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.List;

public record UpdateMyRoutineReqDto(
        @Schema(
                description = "마이루틴 이름",
                example = "내 루틴",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                implementation = String.class
        )
        JsonNullable<String> name,

        @Schema(
                description = "마이루틴 설명",
                example = "설명입니다",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                nullable = true,
                implementation = String.class
        )
        JsonNullable<String> description,

        @Schema(
                description = "마이루틴 공개 범위",
                example = "PUBLIC",
                requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                implementation = Visibility.class
        )
        JsonNullable<Visibility> visibility,

        @ArraySchema(
                schema = @Schema(
                        description = "routine 운동 리스트",
                        implementation = CreateMyRoutineItemReqDto.class
                ),
                arraySchema = @Schema(nullable = true)
        )
        JsonNullable<List<@Valid CreateMyRoutineItemReqDto>> routineList
) {
}
