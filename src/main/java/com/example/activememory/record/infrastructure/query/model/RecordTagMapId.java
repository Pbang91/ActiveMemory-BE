package com.example.activememory.record.infrastructure.query.model;

import com.example.activememory.global.share.converter.RecordIdConverter;
import com.example.activememory.global.share.converter.TagIdConverter;
import com.example.activememory.global.share.id.RecordId;
import com.example.activememory.global.share.id.TagId;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class RecordTagMapId {
    @Convert(converter = RecordIdConverter.class)
    @Column(nullable = false)
    private RecordId recordId;

    @Convert(converter = TagIdConverter.class)
    @Column(nullable = false)
    private TagId tagId;

    protected RecordTagMapId() {}

    public RecordTagMapId(RecordId recordId, TagId tagId) {
        this.recordId = recordId;
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordTagMapId that)) return false;

        return Objects.equals(recordId, that.recordId)
                && Objects.equals(tagId, that.tagId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, tagId);
    }
}
