package com.example.activememory.record.domain.record.entity;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.example.activememory.global.share.converter.UserIdConverter;
import com.example.activememory.global.share.id.ExerciseId;
import com.example.activememory.global.share.id.RecordId;
import com.example.activememory.global.share.id.UserId;
import com.example.activememory.global.share.enums.Visibility;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 30, unique = true)
    private String slug;

    @Column
    private String freeInput;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Visibility visibility;

    @OneToMany(mappedBy = "record", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @OrderBy("displayOrder ASC")
    private List<RecordMetric> metrics = new ArrayList<>();

    @OneToMany(mappedBy = "record")
    @OrderBy("displayOrder ASC")
    private Set<RecordImage> images = new HashSet<>();

    @Convert(converter = UserIdConverter.class)
    @Column(name = "user_id", nullable = false)
    private UserId authorId;

    @Column(nullable = false)
    private LocalDate workoutDate;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Record() {}

    private Record(UserId authorId, String name, String freeInput, Visibility visibility, LocalDate workoutDate) {
        this.authorId = authorId;
        this.name = name;
        this.freeInput = freeInput;
        this.visibility = visibility == null ? Visibility.PUBLIC : visibility;
        this.workoutDate = workoutDate;
    }

    public RecordId getId() {
        return RecordId.of(id);
    }

    public static Record create(UserId authorId, String name, String freeInput, Visibility visibility, LocalDate workoutDate) {
        Objects.requireNonNull(authorId, "authorId는 null이면 안됩니다");
        Objects.requireNonNull(name, "name null이면 안됩니다");

        return new Record(authorId, name, freeInput, visibility, workoutDate == null ? LocalDate.now() : workoutDate);
    }

    public void switchVisibility(Visibility visibility) {
        this.visibility = visibility;

        if (this.visibility == Visibility.PUBLIC && slug == null) {
            slug = NanoIdUtils.randomNanoId();
        }
    }

    public RecordMetric addMetric(ExerciseId exerciseId, Short displayOrder, Short rep, BigDecimal weight, String memo) {
        RecordMetric recordMetric = new RecordMetric(exerciseId, displayOrder, rep, weight, memo);
        recordMetric.assignRecord(this);
        metrics.add(recordMetric);

        return recordMetric;
    }

    public void removeMetric(RecordMetric recordMetric) {
        metrics.remove(recordMetric);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)  return false;
        if (!(o instanceof Record other)) return false;

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
