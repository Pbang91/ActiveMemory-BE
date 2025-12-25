package com.example.activememory.global.converter;

import com.example.activememory.reference.domain.vo.BodyPartCode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BodyPartCodeConverter implements AttributeConverter<BodyPartCode, String> {
    @Override
    public String convertToDatabaseColumn(BodyPartCode bodyPartCode) {
        return bodyPartCode != null ? bodyPartCode.value() : null;
    }

    @Override
    public BodyPartCode convertToEntityAttribute(String dbData) {
        return dbData != null ? BodyPartCode.of(dbData) : null;
    }
}
