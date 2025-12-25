package com.example.activememory.shared.domain.entity;

import com.example.activememory.global.entity.BaseTimeEntity;
import com.example.activememory.shared.domain.vo.GymId;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gyms")
public class Gym extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;

    public GymId getGymId() {
        return GymId.of(id);
    }
}
