package com.example.activememory.global.converter;

import com.example.activememory.record.domain.vo.SessionId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SessionIdConverter implements AttributeConverter<SessionId, Long> {
    @Override
    public Long convertToDatabaseColumn(SessionId sessionId) {
        return sessionId != null ? sessionId.value() : null;
    }

    @Override
    public SessionId convertToEntityAttribute(Long dbData) {
        return dbData != null ? SessionId.of(dbData) : null;
    }
}
