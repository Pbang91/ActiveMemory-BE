package com.example.activememory.record.infrastructure.query.model;

import com.example.activememory.global.share.converter.CategoryIdConverter;
import com.example.activememory.global.share.converter.RecordIdConverter;
import com.example.activememory.global.share.id.CategoryId;
import com.example.activememory.global.share.id.RecordId;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class RecordCategoryMapId {
    @Convert(converter = RecordIdConverter.class)
    @Column(nullable = false)
    private RecordId recordId;

    @Convert(converter = CategoryIdConverter.class)
    @Column(nullable = false, columnDefinition = "uuid")
    private CategoryId categoryId;

    protected RecordCategoryMapId() {}

    public RecordCategoryMapId(RecordId recordId, CategoryId categoryId) {
        this.recordId = recordId;
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordCategoryMapId that)) return false;

        return Objects.equals(recordId, that.recordId)
                && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, categoryId);
    }
}
