package com.example.activememory.global.share.id;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record ExerciseId(UUID value) implements Serializable {
    public ExerciseId(UUID value) {
        this.value = Objects.requireNonNull(value, "value null일 수 없습니다");
    }

    public static ExerciseId of(UUID value) {
        return new ExerciseId(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseId other)) return false;

        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
