package com.example.activememory.global.share.converter;

import com.example.activememory.global.share.id.UserId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class UserIdConverter implements AttributeConverter<UserId, UUID> {
    @Override
    public UUID convertToDatabaseColumn(UserId userId) {
        return userId == null ? null : userId.value();
    }

    @Override
    public UserId convertToEntityAttribute(UUID data) {
        return data == null ? null : UserId.of(data);
    }
}
