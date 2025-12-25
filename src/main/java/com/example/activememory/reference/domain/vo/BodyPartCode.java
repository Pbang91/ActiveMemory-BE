package com.example.activememory.reference.domain.vo;

import com.example.activememory.global.vo.BaseId;

public record BodyPartCode(String value) implements BaseId<String> {
    public static BodyPartCode of(String value) {
        return new BodyPartCode(value);
    }
}
