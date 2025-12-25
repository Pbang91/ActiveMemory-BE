package com.example.activememory.inventory.domain.entity;

import com.example.activememory.reference.domain.enums.MuscleRole;
import com.example.activememory.reference.domain.vo.MuscleId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "custom_machine_muscles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomMachineMuscle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_machine_id", nullable = false)
    private CustomMachine customMachine;

    @Column(name = "muscle_id")
    private MuscleId muscleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MuscleRole role;

    public static CustomMachineMuscle create(CustomMachine customMachine, MuscleId muscleId, MuscleRole role) {
        CustomMachineMuscle mapping = new CustomMachineMuscle();
        mapping.customMachine = customMachine;
        mapping.muscleId = muscleId;
        mapping.role = role;

        return mapping;
    }
}
