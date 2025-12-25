package com.example.activememory.inventory.domain.vo;

import com.example.activememory.global.vo.BaseId;

public record CustomMachineId(Long value) implements BaseId<Long> {
    public static CustomMachineId of(Long value) {
        return new CustomMachineId(value);
    }
}
