package com.example.activememory.global.converter;

import com.example.activememory.inventory.domain.vo.MyGymId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MyGymIdConverter implements AttributeConverter<MyGymId, Long> {
    @Override
    public Long convertToDatabaseColumn(MyGymId myGymId) {
        return myGymId != null ? myGymId.value() : null;
    }

    @Override
    public MyGymId convertToEntityAttribute(Long dbData) {
        return dbData != null ? MyGymId.of(dbData) : null;
    }
}
