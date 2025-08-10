package com.example.activememory.global.share.id;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record UserId(UUID value) implements Serializable {
    public UserId(UUID value) {
        this.value = Objects.requireNonNull(value, "UserId는 null일 수 없습니다");
    }

    public static UserId of(UUID value) {
        return new UserId(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId other)) return false;

        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
