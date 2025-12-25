package com.example.activememory.global.converter;

import com.example.activememory.reference.domain.vo.MuscleId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MuscleIdConverter implements AttributeConverter<MuscleId, Long> {
    @Override
    public Long convertToDatabaseColumn(MuscleId muscleId) {
        return muscleId != null ? muscleId.value() : null;
    }

    @Override
    public MuscleId convertToEntityAttribute(Long dbData) {
        return dbData != null ? MuscleId.of(dbData) : null;
    }
}
