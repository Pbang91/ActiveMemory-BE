package com.example.activememory.global.converter;

import com.example.activememory.inventory.domain.vo.CustomMachineId;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CustomMachineIdConverter extends BaseIdConverter<CustomMachineId, Long> {
    public CustomMachineIdConverter() {
        super(CustomMachineId::of);
    }
}
