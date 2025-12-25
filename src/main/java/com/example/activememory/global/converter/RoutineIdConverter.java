package com.example.activememory.global.converter;

import com.example.activememory.planning.domain.vo.RoutineId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoutineIdConverter implements AttributeConverter<RoutineId, Long> {
    @Override
    public Long convertToDatabaseColumn(RoutineId routineId) {
        return routineId != null ? routineId.value() : null;
    }

    @Override
    public RoutineId convertToEntityAttribute(Long dbData) {
        return dbData != null ? RoutineId.of(dbData) : null;
    }
}
