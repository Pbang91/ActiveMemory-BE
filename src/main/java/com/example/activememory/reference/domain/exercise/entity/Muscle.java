package com.example.activememory.reference.domain.exercise.entity;

import com.example.activememory.reference.domain.exercise.vo.BodyPartCode;
import com.example.activememory.reference.domain.exercise.vo.MuscleId;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "muscles")
public class Muscle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body_part_code", nullable = false, length = 10)
    private BodyPartCode bodyPartCode;

    @Getter
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public MuscleId getMuscleId() {
        return MuscleId.of(id);
    }
}
