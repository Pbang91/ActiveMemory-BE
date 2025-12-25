package com.example.activememory.shared.domain.vo;

import com.example.activememory.global.vo.BaseId;

public record GymId(Long value) implements BaseId<Long> {
    public static GymId of(Long value) {
        return new GymId(value);
    }
}
