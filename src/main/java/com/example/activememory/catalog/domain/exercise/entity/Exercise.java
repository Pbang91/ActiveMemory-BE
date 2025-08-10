package com.example.activememory.catalog.domain.exercise.entity;

import com.example.activememory.global.share.converter.CategoryIdConverter;
import com.example.activememory.global.share.converter.UserIdConverter;
import com.example.activememory.global.share.id.CategoryId;
import com.example.activememory.global.share.id.ExerciseId;
import com.example.activememory.global.share.converter.ExerciseIdConverter;
import com.example.activememory.global.share.id.UserId;
import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "exercise",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_exercise_category_name",
                        columnNames = {"category_id", "name"}
                )
        }
)
public class Exercise {
    @Id
    @Convert(converter = ExerciseIdConverter.class)
    @Column(nullable = false, updatable = false, columnDefinition = "uuid")
    @Getter
    private ExerciseId id;

    @Column(length = 50, nullable = false)
    private String name; // categoryId와 함께 Unique

    @Column
    private String description;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Convert(converter = CategoryIdConverter.class)
    @Column(nullable = false)
    private CategoryId categoryId;

    protected Exercise() {}

    private Exercise(CategoryId categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public static Exercise create(CategoryId categoryId, String name, String description) {
        Objects.requireNonNull(categoryId, "categoryId must not be null");
        Objects.requireNonNull(name, "name must not be null");

        Exercise exercise = new Exercise(categoryId, name, description);

        exercise.id = ExerciseId.of(UuidCreator.getTimeOrderedEpoch());

        return exercise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Exercise other)) return false;

        return other.id.equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
