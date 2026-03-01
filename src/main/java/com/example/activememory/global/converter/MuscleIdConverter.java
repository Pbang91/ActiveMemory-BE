package com.example.activememory.global.converter;

import com.example.activememory.reference.domain.exercise.vo.MuscleId;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MuscleIdConverter extends BaseIdConverter<MuscleId, Long> {
    public MuscleIdConverter() {
        super(MuscleId::of);
    }
}
