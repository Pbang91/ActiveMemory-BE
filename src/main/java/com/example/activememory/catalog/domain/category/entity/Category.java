package com.example.activememory.catalog.domain.category.entity;

import com.example.activememory.global.share.id.CategoryId;
import com.example.activememory.global.share.converter.CategoryIdConverter;
import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Category {
    @Id
    @Convert(converter = CategoryIdConverter.class)
    @Column(nullable = false, updatable = false, columnDefinition = "uuid")
    @Getter
    private CategoryId id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Category() {}

    private Category(String name) {
        this.name = name;
    }

    public static Category create(String name) {
        Objects.requireNonNull(name, "이름은 필수입니다");

        Category category = new Category(name);
        category.id = CategoryId.of(UuidCreator.getTimeOrderedEpoch());

        return category;
    }
}
