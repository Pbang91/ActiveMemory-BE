package com.example.activememory.global.converter;

import com.example.activememory.reference.domain.exercise.vo.BodyPartCode;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BodyPartCodeConverter extends BaseIdConverter<BodyPartCode, String> {
    public BodyPartCodeConverter() {
        super(BodyPartCode::of);
    }
}
