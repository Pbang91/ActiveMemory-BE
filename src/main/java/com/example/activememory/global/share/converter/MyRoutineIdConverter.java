package com.example.activememory.global.share.converter;

import com.example.activememory.global.share.id.MyRoutineId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MyRoutineIdConverter implements AttributeConverter<MyRoutineId, Long> {
    @Override
    public Long convertToDatabaseColumn(MyRoutineId id) {
        return id == null ? null : id.value();
    }

    @Override
    public MyRoutineId convertToEntityAttribute(Long data) {
        return data == null ? null : MyRoutineId.of(data);
    }
}
