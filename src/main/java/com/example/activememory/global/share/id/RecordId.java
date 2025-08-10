package com.example.activememory.global.share.id;

import java.io.Serializable;
import java.util.Objects;

public record RecordId(Long value) implements Serializable {
    public RecordId(Long value) {
        this.value = Objects.requireNonNull(value, "RecordId는 null일 수 없습니다");
    }

    public static RecordId of(Long value) {
        return new RecordId(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordId other)) return false;

        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
