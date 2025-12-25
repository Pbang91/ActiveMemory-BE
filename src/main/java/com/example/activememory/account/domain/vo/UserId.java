package com.example.activememory.account.domain.vo;

import com.example.activememory.global.vo.BaseId;

public record UserId(Long value) implements BaseId<Long> {
    public UserId {
        if (value == null) {
            throw new NullPointerException("value is null");
        }
    }
    
    public static UserId of(Long value) {
        return new UserId(value);
    }
}
