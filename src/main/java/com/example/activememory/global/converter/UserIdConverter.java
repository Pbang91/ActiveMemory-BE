package com.example.activememory.global.converter;

import com.example.activememory.account.user.domain.vo.UserId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserIdConverter extends BaseIdConverter<UserId, Long> {
    public UserIdConverter() {
        super(UserId::of);
    }
}
