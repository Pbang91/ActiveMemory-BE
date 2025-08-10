package com.example.activememory.global.share.converter;

import com.example.activememory.global.share.id.CategoryId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class CategoryIdConverter implements AttributeConverter<CategoryId, UUID> {
    @Override
    public UUID convertToDatabaseColumn(CategoryId categoryId) {
        return categoryId == null ? null : categoryId.value();
    }

    @Override
    public CategoryId convertToEntityAttribute(UUID data) {
        return data == null ? null : CategoryId.of(data);
    }
}
