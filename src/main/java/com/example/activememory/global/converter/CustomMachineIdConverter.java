package com.example.activememory.global.converter;

import com.example.activememory.inventory.domain.vo.CustomMachineId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CustomMachineIdConverter implements AttributeConverter<CustomMachineId, Long> {
    @Override
    public Long convertToDatabaseColumn(CustomMachineId customMachineId) {
        return customMachineId != null ? customMachineId.value() : null;
    }

    @Override
    public CustomMachineId convertToEntityAttribute(Long dbData) {
        return dbData != null ? CustomMachineId.of(dbData) : null;
    }
}
