package com.example.activememory.reference.domain.vo;

import com.example.activememory.global.vo.BaseId;

public record MuscleId(Long value) implements BaseId<Long> {
    public static MuscleId of(Long value) {
        return new MuscleId(value);
    }
}
