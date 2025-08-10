package com.example.activememory.record.domain.like.entity;

import com.example.activememory.global.share.converter.RecordIdConverter;
import com.example.activememory.global.share.converter.UserIdConverter;
import com.example.activememory.global.share.id.RecordId;
import com.example.activememory.global.share.id.UserId;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "like",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_like_record_user",
                        columnNames = {"record_id", "user_id"}
                )
        }
)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = RecordIdConverter.class)
    @Column(nullable = false)
    private RecordId recordId;

    @Convert(converter = UserIdConverter.class)
    @Column(nullable = false)
    private UserId userId;

    @CreatedDate
    private LocalDateTime createdAt;

    protected Like() {}

    private Like(RecordId recordId, UserId userId) {
        this.recordId = recordId;
        this.userId = userId;
    }

    public static Like create(RecordId recordId, UserId userId) {
        Objects.requireNonNull(recordId, "recordId is null");
        Objects.requireNonNull(userId, "userId is null");

        return new Like(recordId, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Like other)) return false;

        return Objects.equals(other.id, id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
