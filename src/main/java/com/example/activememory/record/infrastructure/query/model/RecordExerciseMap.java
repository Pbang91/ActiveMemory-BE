package com.example.activememory.record.infrastructure.query.model;

import com.example.activememory.catalog.domain.exercise.entity.Exercise;
import com.example.activememory.record.domain.record.entity.Record;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class RecordExerciseMap {
    @EmbeddedId
    private RecordExerciseMapId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("recordId")
    @JoinColumn(name = "record_id", nullable = false)
    private Record record;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("exerciseId")
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    protected RecordExerciseMap() {}

    private RecordExerciseMap(Record record, Exercise exercise) {
        this.record = record;
        this.exercise = exercise;

        this.id = new RecordExerciseMapId(record.getId(), exercise.getId());
    }

    public static RecordExerciseMap link(Record record, Exercise exercise) {
        return new RecordExerciseMap(record, exercise);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof  RecordExerciseMap that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
