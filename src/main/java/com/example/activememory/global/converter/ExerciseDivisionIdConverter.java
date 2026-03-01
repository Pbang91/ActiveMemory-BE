package com.example.activememory.global.converter;

import com.example.activememory.planning.domain.vo.ExerciseDivisionId;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ExerciseDivisionIdConverter extends BaseIdConverter<ExerciseDivisionId, Long> {
    public ExerciseDivisionIdConverter() {
        super(ExerciseDivisionId::of);
    }
}
