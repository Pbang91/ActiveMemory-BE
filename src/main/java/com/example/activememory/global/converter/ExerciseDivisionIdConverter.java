package com.example.activememory.global.converter;

import com.example.activememory.planning.domain.vo.ExerciseDivisionId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ExerciseDivisionIdConverter implements AttributeConverter<ExerciseDivisionId, Long> {
    @Override
    public Long convertToDatabaseColumn(ExerciseDivisionId exerciseDivisionId) {
        return exerciseDivisionId != null ? exerciseDivisionId.value() : null;
    }

    @Override
    public ExerciseDivisionId convertToEntityAttribute(Long dbData) {
        return dbData != null ? ExerciseDivisionId.of(dbData) : null;
    }
}
