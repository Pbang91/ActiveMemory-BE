package com.example.activememory.record.domain.tag.entity;

import com.example.activememory.global.share.converter.TagIdConverter;
import com.example.activememory.global.share.id.TagId;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    protected Tag() {}

    private Tag(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public TagId getId() {
        return TagId.of(id);
    }

    public static Tag create(String name) {
        return new Tag(name);
    }

    @Override public boolean equals(Object o) { return (this == o) || (o instanceof Tag t && Objects.equals(id, t.id)); }
    @Override public int hashCode() { return Objects.hash(id); }
}
