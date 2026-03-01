package com.example.activememory.inventory.domain.entity;

import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.global.entity.BaseTimeEntity;
import com.example.activememory.inventory.domain.vo.MyGymId;
import com.example.activememory.reference.domain.gym.vo.GymId;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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
}

