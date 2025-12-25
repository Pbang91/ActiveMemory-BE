package com.example.activememory.global.converter;

import com.example.activememory.account.domain.vo.UserId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserIdConverter implements AttributeConverter<UserId, Long> {
    @Override
    public Long convertToDatabaseColumn(UserId id) {
        return id != null ? id.value() : null;
    }

    @Override
    public UserId convertToEntityAttribute(Long dbData) {
        return dbData != null ? UserId.of(dbData) : null;
    }
}
