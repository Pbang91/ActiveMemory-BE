package com.example.activememory.inventory.application.command.dto;

import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.inventory.domain.vo.MyGymId;
import com.example.activememory.reference.domain.exercise.enums.MuscleRole;
import com.example.activememory.reference.domain.exercise.vo.BodyPartCode;
import com.example.activememory.reference.domain.exercise.vo.MuscleId;
import com.example.activememory.reference.domain.exercise.vo.StandardExerciseId;

import java.util.List;

public record RegisterMyGymMachineCommand(
        UserId userId,
        MyGymId myGymId,
        String name,
        StandardExerciseId standardExerciseId,
        BodyPartCode bodyPartCode,
        List<MuscleMappingData> muscleMappingDataList,
        String memo
) {
    public record MuscleMappingData(
            MuscleId muscleId,
            MuscleRole role
    ) {
    }
}
