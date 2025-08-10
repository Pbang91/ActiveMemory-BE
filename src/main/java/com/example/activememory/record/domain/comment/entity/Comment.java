package com.example.activememory.record.domain.comment.entity;

import com.example.activememory.global.share.converter.RecordIdConverter;
import com.example.activememory.global.share.converter.UserIdConverter;
import com.example.activememory.global.share.id.RecordId;
import com.example.activememory.global.share.id.UserId;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = RecordIdConverter.class)
    @Column(nullable = false)
    private RecordId recordId;

    @Convert(converter = UserIdConverter.class)
    @Column(name = "user_id", nullable = false)
    private UserId commenterId;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Comment() {}

    private Comment(RecordId recordId, UserId commenterId, String content) {
        this.recordId = recordId;
        this.commenterId = commenterId;
        this.content = content;
    }

    public static Comment create(RecordId recordId, UserId commenterId, String content) {
        Objects.requireNonNull(recordId, "recordId must not be null");
        Objects.requireNonNull(commenterId, "commenterId cannot be null");
        Objects.requireNonNull(content, "content cannot be null");

        return new Comment(recordId, commenterId, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment other)) return false;

        return Objects.equals(other.id, id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
