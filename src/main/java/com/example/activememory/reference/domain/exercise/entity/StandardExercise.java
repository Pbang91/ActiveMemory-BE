package com.example.activememory.reference.domain.exercise.entity;

import com.example.activememory.global.enums.ExerciseType;
import com.example.activememory.reference.domain.exercise.enums.MuscleRole;
import com.example.activememory.reference.domain.exercise.vo.BodyPartCode;
import com.example.activememory.reference.domain.exercise.vo.StandardExerciseId;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "standard_exercises")
public class StandardExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "body_part_code", nullable = false, length = 10)
    private BodyPartCode bodyPartCode;

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private ExerciseType type;

    @Getter
    @Column(name = "description")
    private String description;

    @Getter
    @OneToMany(mappedBy = "standardExercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StandardExerciseMuscle> exerciseMuscles = new ArrayList<>();

    public StandardExerciseId getStandardExerciseId() {
        return StandardExerciseId.of(id);
    }

    public void addMuscle(Muscle muscle, MuscleRole role) {
        StandardExerciseMuscle mapping = StandardExerciseMuscle.create(this, muscle, role);
        this.exerciseMuscles.add(mapping);
    }
}
