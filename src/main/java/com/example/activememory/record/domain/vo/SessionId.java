package com.example.activememory.record.domain.vo;

import com.example.activememory.global.vo.BaseId;

public record SessionId(Long value) implements BaseId<Long> {
    public static SessionId of(Long value) {
        return new SessionId(value);
    }
}
