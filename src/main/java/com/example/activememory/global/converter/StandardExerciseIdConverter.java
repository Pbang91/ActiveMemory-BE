package com.example.activememory.global.converter;

import com.example.activememory.reference.domain.vo.StandardExerciseId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StandardExerciseIdConverter implements AttributeConverter<StandardExerciseId, Long> {
    @Override
    public Long convertToDatabaseColumn(StandardExerciseId standardExerciseId) {
        return standardExerciseId != null ? standardExerciseId.value() : null;
    }

    @Override
    public StandardExerciseId convertToEntityAttribute(Long dbData) {
        return dbData != null ? StandardExerciseId.of(dbData) : null;
    }
}
