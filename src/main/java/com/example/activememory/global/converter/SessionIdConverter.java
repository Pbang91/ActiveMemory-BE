package com.example.activememory.global.converter;

import com.example.activememory.record.domain.vo.SessionId;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SessionIdConverter extends BaseIdConverter<SessionId, Long> {
    public SessionIdConverter() {
        super(SessionId::of);
    }
}
