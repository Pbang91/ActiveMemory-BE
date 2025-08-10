package com.example.activememory.global.share.converter;

import com.example.activememory.global.share.id.TagId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TagIdConverter implements AttributeConverter<TagId, Long> {
    @Override
    public Long convertToDatabaseColumn(TagId tagId) {
        return tagId == null ? null : tagId.value();
    }

    @Override
    public TagId convertToEntityAttribute(Long data) {
        return data == null ? null : TagId.of(data);
    }
}
