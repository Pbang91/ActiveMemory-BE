package com.example.activememory.user.presentation.command.dto.request;

import com.example.activememory.global.share.enums.Visibility;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateMyRoutineReqDto(
        @Schema(description = "마이루틴 이름", example = "내 루틴", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String name,

        @Schema(description = "마이루틴 설명", example = "내 루틴", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        String description,

        @Schema(description = "마이루틴 공개 범위", example = "PUBLIC", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull
        Visibility visibility,

        @ArraySchema(
                schema = @Schema(
                        description = "routine 운동 리스트",
                        implementation = CreateMyRoutineItemReqDto.class,
                        requiredMode = Schema.RequiredMode.REQUIRED
                )
        )
        @NotEmpty
        List<@Valid CreateMyRoutineItemReqDto> routineList
) {
}
