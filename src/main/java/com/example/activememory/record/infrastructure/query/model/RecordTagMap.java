package com.example.activememory.record.infrastructure.query.model;

import com.example.activememory.record.domain.record.entity.Record;
import com.example.activememory.record.domain.tag.entity.Tag;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class RecordTagMap {
    @EmbeddedId
    private RecordTagMapId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("recordId")
    @JoinColumn(name = "record_id", nullable = false)
    private Record record;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("tagId")
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    protected RecordTagMap() {}

    private RecordTagMap(Record record, Tag tag) {
        this.record = record;
        this.tag = tag;

        this.id = new RecordTagMapId(record.getId(), tag.getId());
    }

    public static RecordTagMap link(Record record, Tag tag) {
        return new RecordTagMap(record, tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof RecordTagMap that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
