package com.example.activememory.planning.domain.entity;

import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.global.entity.BaseTimeEntity;
import com.example.activememory.planning.domain.vo.RoutineId;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Table(name = "routines")
public class Routine extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private UserId userId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ColumnDefault("1")
    @Column(name = "current_division_sequence", nullable = false)
    private Integer currentDivisionSequence;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    private List<ExerciseDivision> exerciseDivisions;

    public RoutineId getRoutineId() {
        return RoutineId.of(id);
    }
}
