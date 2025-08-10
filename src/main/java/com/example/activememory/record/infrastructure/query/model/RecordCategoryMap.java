package com.example.activememory.record.infrastructure.query.model;

import com.example.activememory.catalog.domain.category.entity.Category;
import com.example.activememory.record.domain.record.entity.Record;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class RecordCategoryMap {
    @EmbeddedId
    RecordCategoryMapId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("recordId")
    @JoinColumn(name = "record_id", nullable = false)
    private Record record;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    protected RecordCategoryMap() {}

    private RecordCategoryMap(Record record, Category category) {
        this.record = record;
        this.category = category;

        this.id = new RecordCategoryMapId(record.getId(), category.getId());
    }

    public static RecordCategoryMap link(Record record, Category category) {
        return new RecordCategoryMap(record, category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof RecordCategoryMap that && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
