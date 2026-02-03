package com.example.activememory.reference.domain.gym.entity;

import com.example.activememory.global.entity.BaseTimeEntity;
import com.example.activememory.reference.domain.gym.vo.GymId;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "gyms")
public class Gym extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider_id", unique = true, length = 30)
    private String providerId; // 카카오,네이버 고유 ID(ex. kakao_123456)

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Column(nullable = false)
    private String address;

    @Column(name = "deleted_at")
    private LocalDateTime deleteAt;

    @Getter
    @Column(name = "latitude", length = 50)
    private String latitude;

    @Getter
    @Column(name = "longitude", length = 50)
    private String longitude;

    public GymId getGymId() {
        return GymId.of(id);
    }

    protected Gym() {
    }

    private Gym(String providerId, String name, String address, String latitude, String longitude) {
        this.providerId = providerId;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Gym of(String providerId, String name, String address, String latitude, String longitude) {
        return new Gym(providerId, name, address, latitude, longitude);
    }

    public void delete() {
        this.deleteAt = LocalDateTime.now();
    }
}
