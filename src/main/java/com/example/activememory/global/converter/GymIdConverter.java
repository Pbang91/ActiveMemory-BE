package com.example.activememory.global.converter;

import com.example.activememory.shared.domain.vo.GymId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GymIdConverter implements AttributeConverter<GymId, Long> {
    @Override
    public Long convertToDatabaseColumn(GymId gymId) {
        return gymId != null ? gymId.value() : null;
    }

    @Override
    public GymId convertToEntityAttribute(Long dbData) {
        return dbData != null ? GymId.of(dbData) : null;
    }
}
