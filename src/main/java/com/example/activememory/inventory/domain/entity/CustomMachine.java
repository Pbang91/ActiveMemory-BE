package com.example.activememory.inventory.domain.entity;

import com.example.activememory.global.entity.BaseTimeEntity;
import com.example.activememory.inventory.domain.vo.CustomMachineId;
import com.example.activememory.reference.domain.enums.MuscleRole;
import com.example.activememory.reference.domain.vo.BodyPartCode;
import com.example.activememory.reference.domain.vo.MuscleId;
import com.example.activememory.reference.domain.vo.StandardExerciseId;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "custom_machines")
public class CustomMachine extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_gym_id", nullable = false)
    private MyGym myGym;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "memo")
    private String memo;

    @Column(name = "standard_exercise_id", nullable = false)
    private StandardExerciseId standardExerciseId;

    @Column(name = "body_part_code", nullable = false)
    private BodyPartCode bodyPartCode;

    @OneToMany(mappedBy = "customMachine", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomMachineMuscle> muscles = new ArrayList<>();

    public CustomMachineId getCustomMachineId() {
        return CustomMachineId.of(id);
    }

    public void addMuscle(MuscleId muscleId, MuscleRole role) {
        CustomMachineMuscle mapping = CustomMachineMuscle.create(this, muscleId, role);
        this.muscles.add(mapping);
    }
}
