package com.example.activememory.global.converter;

import com.example.activememory.reference.domain.exercise.vo.StandardExerciseId;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StandardExerciseIdConverter extends BaseIdConverter<StandardExerciseId, Long> {
    public StandardExerciseIdConverter() {
        super(StandardExerciseId::of);
    }
}
