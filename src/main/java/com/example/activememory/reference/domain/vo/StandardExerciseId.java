package com.example.activememory.reference.domain.vo;

import com.example.activememory.global.vo.BaseId;

public record StandardExerciseId(Long value) implements BaseId<Long> {
    public static StandardExerciseId of(Long value) {
        return new StandardExerciseId(value);
    }
}
