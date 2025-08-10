package com.example.activememory.global.share.converter;

import com.example.activememory.global.share.id.RecordId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RecordIdConverter implements AttributeConverter<RecordId, Long> {
    @Override
    public Long convertToDatabaseColumn(RecordId recordId) {
        return recordId == null ? null : recordId.value();
    }

    @Override
    public RecordId convertToEntityAttribute(Long data) {
        return data == null ? null : RecordId.of(data);
    }
}
