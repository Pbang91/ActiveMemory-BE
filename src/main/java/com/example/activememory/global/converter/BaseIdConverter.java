package com.example.activememory.global.converter;

import com.example.activememory.global.vo.BaseId;
import jakarta.persistence.AttributeConverter;

import java.util.function.Function;

// T: 감싸진 VO타입(ex. GymId)
// V: 실제 DB 컬럼 타입(ex. Long, String)
public abstract class BaseIdConverter<T extends BaseId<V>, V> implements AttributeConverter<T, V> {
    // V 타입을 받아서 T 타입을 만들어주는 함수 (예: GymId::of)
    private final Function<V, T> factory;

    protected BaseIdConverter(Function<V, T> factory) {
        this.factory = factory;
    }

    @Override
    public V convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.value() : null;
    }

    @Override
    public T convertToEntityAttribute(V dbData) {
        return dbData != null ? factory.apply(dbData) : null;
    }
}
