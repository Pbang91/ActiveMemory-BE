package com.example.activememory.planning.domain.vo;

import com.example.activememory.global.vo.BaseId;

public record ExerciseDivisionId(Long value) implements BaseId<Long> {
    public static ExerciseDivisionId of(Long value) {
        return new ExerciseDivisionId(value);
    }
}
