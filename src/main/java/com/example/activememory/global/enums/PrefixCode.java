package com.example.activememory.global.enums;

public enum PrefixCode {
    AUTH_TEMP_CODE("auth:temp"),
    ACTIVE_DEVICE("active:user");

    private final String code;

    PrefixCode(String code) {
        this.code = code;
    }
}
