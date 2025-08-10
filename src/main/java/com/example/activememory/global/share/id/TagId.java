package com.example.activememory.global.share.id;

import java.io.Serializable;
import java.util.Objects;

public record TagId(Long value) implements Serializable {
    public TagId(Long value) {
        this.value = Objects.requireNonNull(value, "value must not be null");
    }

    public static TagId of(Long value) {
        return new TagId(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagId other)) return false;

        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
