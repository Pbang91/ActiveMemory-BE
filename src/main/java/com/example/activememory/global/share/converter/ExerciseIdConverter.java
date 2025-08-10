package com.example.activememory.global.share.converter;

import com.example.activememory.global.share.id.ExerciseId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class ExerciseIdConverter implements AttributeConverter<ExerciseId, UUID> {
    @Override
    public UUID convertToDatabaseColumn(ExerciseId exerciseId) {
        return exerciseId == null ? null : exerciseId.value();
    }

    @Override
    public ExerciseId convertToEntityAttribute(UUID data) {
        return data == null ? null : ExerciseId.of(data);
    }
}
