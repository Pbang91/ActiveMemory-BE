package com.example.activememory.reference.domain.entity;

import com.example.activememory.reference.domain.vo.BodyPartCode;
import com.example.activememory.reference.domain.vo.MuscleId;
import jakarta.persistence.*;

@Entity
@Table(name = "muscles")
public class Muscle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body_part_code", nullable = false, length = 10)
    private BodyPartCode bodyPartCode;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public MuscleId getMuscleId() {
        return MuscleId.of(id);
    }
}
