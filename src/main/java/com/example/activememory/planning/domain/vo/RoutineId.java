package com.example.activememory.planning.domain.vo;

import com.example.activememory.global.vo.BaseId;

public record RoutineId(Long value) implements BaseId<Long> {
    public static RoutineId of(Long value) {
        return new RoutineId(value);
    }
}
