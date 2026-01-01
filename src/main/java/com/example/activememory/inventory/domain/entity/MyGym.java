package com.example.activememory.inventory.domain.entity;

import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.global.entity.BaseTimeEntity;
import com.example.activememory.inventory.domain.vo.MyGymId;
import com.example.activememory.shared.domain.vo.GymId;
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

    public MyGymId getMyGymId() {
        return MyGymId.of(id);
    }
}

