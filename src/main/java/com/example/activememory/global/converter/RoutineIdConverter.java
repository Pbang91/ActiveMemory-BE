package com.example.activememory.global.converter;

import com.example.activememory.planning.domain.vo.RoutineId;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoutineIdConverter extends BaseIdConverter<RoutineId, Long> {
    public RoutineIdConverter() {
        super(RoutineId::of);
    }
}
