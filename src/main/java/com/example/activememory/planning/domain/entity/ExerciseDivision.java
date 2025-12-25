package com.example.activememory.planning.domain.entity;

import com.example.activememory.planning.domain.vo.ExerciseDivisionId;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "exercise_divisions")
public class ExerciseDivision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @OneToMany(mappedBy = "exerciseDivision")
    private List<ExerciseDivisionStep> steps;

    public ExerciseDivisionId getExerciseDivisionId() {
        return ExerciseDivisionId.of(id);
    }
}
