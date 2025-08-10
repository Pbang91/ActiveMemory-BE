package com.example.activememory.record.infrastructure.query.model;

import com.example.activememory.global.share.converter.ExerciseIdConverter;
import com.example.activememory.global.share.converter.RecordIdConverter;
import com.example.activememory.global.share.id.ExerciseId;
import com.example.activememory.global.share.id.RecordId;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class RecordExerciseMapId {
    @Convert(converter = RecordIdConverter.class)
    @Column(nullable = false)
    private RecordId recordId;

    @Convert(converter = ExerciseIdConverter.class)
    @Column(nullable = false, columnDefinition = "uuid")
    private ExerciseId exerciseId;

    protected RecordExerciseMapId() {}

    public RecordExerciseMapId(RecordId recordId, ExerciseId exerciseId) {
        this.recordId = recordId;
        this.exerciseId = exerciseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof RecordExerciseMapId that)) return false;

        return Objects.equals(recordId, that.recordId) && Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, exerciseId);
    }
}
