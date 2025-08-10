package com.example.activememory.global.share.id;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record CategoryId(UUID value) implements Serializable {
    public CategoryId(UUID value) {
        this.value = Objects.requireNonNull(value, "ActivityId는 null일 수 없습니다");
    }

    public static CategoryId of(UUID value) {
        return new CategoryId(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryId other)) return false;

        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
