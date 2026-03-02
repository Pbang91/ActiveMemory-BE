package com.example.activememory.global.vo;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public interface BaseId<T> extends Serializable {
    @JsonValue
    T value();
}
