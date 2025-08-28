package com.example.activememory.user.application.query.dto.response;

import com.example.activememory.global.exception.CustomException;
import com.example.activememory.global.exception.ExceptionCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record MyRoutineItemResDto(
        @Schema(description = "Exercise id", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull UUID exerciseId,

        @Schema(description = "Exercise name", example = "벨트 스쿼트", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank String exerciseName,

        @Schema(description = "MyRoutine의 특정 운동의 목표 셋트", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull Short set,

        @Schema(description = "MyRoutine의 특정 운동의 셋트별 목표 횟수", example = "12", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull Short rep,

        @Schema(description = "MyRoutine의 특정 운동의 횟수당 무게. 기본 단위 kg", example = "125.50", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull BigDecimal weight,

        @Schema(description = "MyRoutine의 특정 운동의 메모", example = "벨트 스쿼트할 때 5회쯤 부터 자꾸 힘이 딸림", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String memo
) {
    public MyRoutineItemResDto(
            UUID exerciseId,
            String exerciseName,
            Short set,
            Short rep,
            BigDecimal weight,
            String memo
    ) {
        if (exerciseId == null || exerciseName == null || set == null || rep == null || weight == null) {
            throw new CustomException(ExceptionCode.INTERNAL_SERVER_ERROR, "뭔가 아주 단단히 잘못됨");
        }

        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.set = set;
        this.rep = rep;
        this.weight = weight;
        this.memo = memo;
    }
}
