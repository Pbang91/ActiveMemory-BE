package com.example.activememory.planning.domain.entity;

import com.example.activememory.global.enums.PerformableType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "exercise_division_steps")
public class ExerciseDivisionStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "step_order", nullable = false)
    private Integer stepOrder;

    @Column(name = "target_sets", nullable = false)
    private Integer targetSets;

    @Column(name = "target_reps", nullable = false)
    private Integer targetReps;

    @Column(name = "target_weight", precision = 5, scale = 2)
    private BigDecimal targetWeight;

    @Enumerated(EnumType.STRING)
    @Column(name = "performable_type", nullable = false, length = 10)
    private PerformableType performableType;

    @Column(name = "performable_id", nullable = false)
    private Long performableId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_division_id", nullable = false)
    private ExerciseDivision exerciseDivision;
}
