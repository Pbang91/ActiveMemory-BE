package com.example.activememory.inventory.domain.vo;

import com.example.activememory.global.vo.BaseId;

public record MyGymId(Long value) implements BaseId<Long> {
    public static MyGymId of(Long value) {
        return new MyGymId(value);
    }
}
