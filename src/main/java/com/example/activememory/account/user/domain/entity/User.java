package com.example.activememory.account.user.domain.entity;

import com.example.activememory.account.user.domain.vo.Profile;
import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.global.entity.BaseTimeEntity;
import com.example.activememory.global.enums.AuthType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 20)
    private AuthType type;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Getter
    @Embedded
    private Profile profile;

    protected User() {
    }

    private User(AuthType type, String email, String encryptedPassword, Profile profile) {
        this.type = type;
        this.email = email;
        this.password = encryptedPassword;
        this.profile = profile;
    }

    public UserId getUserId() {
        return UserId.of(id);
    }

    public static User of(AuthType type, String email, String encryptedPassword, String nickname, String bio) {
        return new User(type, email, encryptedPassword, Profile.of(nickname, bio));
    }
}
