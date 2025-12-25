package com.example.activememory.global.vo;

import java.io.Serializable;

public interface BaseId<T> extends Serializable {
    T value();
}
