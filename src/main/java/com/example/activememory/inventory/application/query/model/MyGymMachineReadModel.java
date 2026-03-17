package com.example.activememory.inventory.application.query.model;

import com.example.activememory.reference.domain.exercise.enums.MuscleRole;
import com.example.activememory.reference.domain.exercise.vo.BodyPartCode;
import com.example.activememory.reference.domain.exercise.vo.MuscleId;
import com.example.activememory.reference.domain.exercise.vo.StandardExerciseId;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MyGymMachineReadModel(
        @NotNull
        @Schema(description = "체육관 기구 id", example = "1")
        Long customMachineId,

        @NotNull
        @Schema(description = "체육관 기구명", example = "싸이벡 종합 풀다운 머신")
        String name,

        @Schema(description = "기구 메모", example = "와이드 그립으로 주로 진행")
        String memo,

        @NotNull
        @Schema(description = "근육 대분류 종류", implementation = BodyPart.class)
        BodyPart bodyPart,

        @NotNull
        @Schema(description = "연관된 기본 운동 정보", implementation = StandardExercise.class)
        StandardExercise standardExercise,

        @ArraySchema(schema = @Schema(description = "기구에 사용되는 근육 정보", implementation = Muscle.class))
        List<Muscle> muscles
) {
    public record BodyPart(
            @NotNull
            @Schema(description = "대분류 CODE", example = "CHEST")
            BodyPartCode code,

            @NotNull
            @Schema(description = "대분류 한글 이름", example = "가슴")
            String name
    ) {
    }

    public record StandardExercise(
            @NotNull
            @Schema(description = "standardExercise id", example = "20")
            StandardExerciseId standardExerciseId,

            @NotNull
            @Schema(description = "운동명", example = "렛풀다운")
            String name
    ) {
    }

    public record Muscle(
            @NotNull
            @Schema(description = "muscle id", example = "20")
            MuscleId muscleId,

            @NotNull
            @Schema(description = "근육명", example = "대흉근")
            String name,

            @NotNull
            @Schema(description = "주동근 or 협력근", example = "PRIMARY")
            MuscleRole role
    ) {
    }
}
