package com.example.activememory.reference.domain.entity;

import com.example.activememory.reference.domain.enums.MuscleRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "standard_exercise_muscles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StandardExerciseMuscle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "standard_exercise_id", nullable = false)
    private StandardExercise standardExercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "muscle_id", nullable = false)
    private Muscle muscle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MuscleRole role;

    public static StandardExerciseMuscle create(StandardExercise standardExercise, Muscle muscle, MuscleRole role) {
        StandardExerciseMuscle mapping = new StandardExerciseMuscle();
        mapping.standardExercise = standardExercise;
        mapping.muscle = muscle;
        mapping.role = role;

        return mapping;
    }
}
