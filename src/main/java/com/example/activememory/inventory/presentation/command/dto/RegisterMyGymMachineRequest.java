package com.example.activememory.inventory.presentation.command.dto;

import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.inventory.application.command.dto.RegisterMyGymMachineCommand;
import com.example.activememory.inventory.domain.vo.MyGymId;
import com.example.activememory.reference.domain.exercise.enums.MuscleRole;
import com.example.activememory.reference.domain.exercise.vo.BodyPartCode;
import com.example.activememory.reference.domain.exercise.vo.MuscleId;
import com.example.activememory.reference.domain.exercise.vo.StandardExerciseId;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Collections;
import java.util.List;

public record RegisterMyGymMachineRequest(
        @NotBlank
        @Schema(description = "기구 이름", example = "파라마운트 숄더 프레스")
        String name,

        @NotNull
        @Schema(description = "기본 운동 Id", example = "20")
        Long standardExerciseId,

        @NotNull
        @Schema(description = "근육 대분류", example = "SHOULDER")
        String bodyPartCode,

        @ArraySchema(schema = @Schema(implementation = MuscleMappingData.class, description = "근육 정보 매핑 배열"))
        List<MuscleMappingData> muscleMappingDataList,

        @Schema(description = "메모 사항", example = "시트 세팅 3으로 설정해서 진행하면 느낌 좋음")
        String memo
) {
    public record MuscleMappingData(
            @NotNull
            @Schema(description = "소분류 근육 Id", example = "1")
            Long muscleId,

            @NotNull
            @Schema(description = "소분류 근육 역할", example = "PRIMARY")
            MuscleRole role
    ) {
    }

    public RegisterMyGymMachineCommand toCommand(Long userId, Long myGymId) {
        return new RegisterMyGymMachineCommand(
                UserId.of(userId),
                MyGymId.of(myGymId),
                name,
                StandardExerciseId.of(standardExerciseId),
                BodyPartCode.of(bodyPartCode),
                muscleMappingDataList == null
                        ? Collections.emptyList()
                        : muscleMappingDataList
                        .stream()
                        .map((data) ->
                                new RegisterMyGymMachineCommand.MuscleMappingData(
                                        MuscleId.of(data.muscleId()),
                                        data.role()
                                )
                        )
                        .toList(),
                memo
        );
    }
}
