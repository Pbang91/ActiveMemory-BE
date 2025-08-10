package com.example.activememory.global.share.id;

import java.io.Serializable;
import java.util.Objects;

public record MyRoutineId(Long value) implements Serializable {
    public MyRoutineId(Long value) {
        this.value = Objects.requireNonNull(value, "value must not be null");
    }

    public static MyRoutineId of(Long value) {
        return new MyRoutineId(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyRoutineId other)) return false;

        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
