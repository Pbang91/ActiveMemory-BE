package com.example.activememory.account.user.domain.entity;

import com.example.activememory.account.user.domain.vo.Profile;
import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Getter
    @Embedded
    private Profile profile;

    public UserId getUserId() {
        return UserId.of(id);
    }
}
