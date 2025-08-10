package com.example.activememory.user.domain.follow.entity;

import com.example.activememory.global.share.converter.UserIdConverter;
import com.example.activememory.global.share.id.UserId;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "follow",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_follow_pair",
                        columnNames = {"follower_id", "following_id"}
                )
        }
)
@Check(constraints = "follower_id <> following_id")
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Convert(converter = UserIdConverter.class)
    @Column(name = "follower_id", nullable = false)
    private UserId followerId;

    @Convert(converter = UserIdConverter.class)
    @Column(name = "following_id", nullable = false)
    private UserId followingId;

    @CreatedDate
    private LocalDateTime createdAt;

    protected Follow() {}

    private Follow(UserId followerId, UserId followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public static Follow create(UserId followerId, UserId followingId) {
        Objects.requireNonNull(followerId, "FollowerId는 필수입나다");
        Objects.requireNonNull(followingId, "FollowingId는 필수입니다");

        if (followerId.equals(followingId)) {
            // TODO: 같을 수 없음 예외
        }

        return new Follow(followerId, followingId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)  return false;
        if (!(o instanceof Follow other)) return false;

        return Objects.equals(other.id, id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
