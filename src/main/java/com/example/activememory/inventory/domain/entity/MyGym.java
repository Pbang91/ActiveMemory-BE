package com.example.activememory.inventory.domain.entity;

import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.global.entity.BaseTimeEntity;
import com.example.activememory.global.exception.BusinessException;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.inventory.domain.vo.MyGymId;
import com.example.activememory.reference.domain.exercise.vo.BodyPartCode;
import com.example.activememory.reference.domain.exercise.vo.StandardExerciseId;
import com.example.activememory.reference.domain.gym.vo.GymId;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "my_gyms")
public class MyGym extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private UserId userId;

    @Column(name = "gym_id", nullable = false)
    private GymId gymId;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "myGym", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomMachine> machines = new ArrayList<>();

    protected MyGym() {
    }

    private MyGym(UserId userId, GymId gymId) {
        this.userId = userId;
        this.gymId = gymId;
        this.isActive = true;
    }

    public static MyGym create(UserId userId, GymId gymId) {
        return new MyGym(userId, gymId);
    }

    public MyGymId getMyGymId() {
        return MyGymId.of(id);
    }

    public CustomMachine addMachine(String name, StandardExerciseId standardExerciseId, BodyPartCode bodyPartCode, String memo) {
        boolean isDuplicate = this.machines.stream()
                .anyMatch(machine -> machine.getName().equals(name));

        if (isDuplicate) {
            throw new BusinessException(ExceptionCode.ALREADY_EXIST_CUSTOM_MACHINE);
        }

        CustomMachine customMachine = CustomMachine.create(this, name, standardExerciseId, bodyPartCode, memo);
        this.machines.add(customMachine);

        return customMachine;
    }
}

