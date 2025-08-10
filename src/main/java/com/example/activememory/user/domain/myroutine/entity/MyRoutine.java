package com.example.activememory.user.domain.myroutine.entity;

import com.example.activememory.global.share.converter.MyRoutineIdConverter;
import com.example.activememory.global.share.converter.UserIdConverter;
import com.example.activememory.global.share.id.ExerciseId;
import com.example.activememory.global.share.id.MyRoutineId;
import com.example.activememory.global.share.id.UserId;
import com.example.activememory.global.share.enums.Visibility;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class MyRoutine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = UserIdConverter.class)
    @Column(name = "user_id", nullable = false)
    private UserId userId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Visibility visibility;

    @OneToMany(mappedBy = "myRoutine", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MyRoutineItem> items = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    protected MyRoutine() {}

    private MyRoutine(UserId userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public MyRoutineId getId() {
        return MyRoutineId.of(id);
    }

    public static MyRoutine register(UserId userId, String name) {
        Objects.requireNonNull(userId, "UserId는 필수입니다");
        Objects.requireNonNull(name, "템플릿 이름은 필수입니다");

        return new MyRoutine(userId, name);
    }

    public MyRoutineItem addItem(
            @NotNull ExerciseId exerciseId,
            @NotNull Short targetSet,
            @NotNull Short targetRep,
            @NotNull BigDecimal targetWeight,
            String memo
    ) {
        boolean duplicated = items.stream().anyMatch(i -> i.getExerciseId().equals(exerciseId));

        if (duplicated) {
            // TODO: 이미 등록된 운동
        }

        MyRoutineItem item = new MyRoutineItem(exerciseId, targetSet, targetRep, targetWeight, memo);
        item.assignMyRoutine(this);
        items.add(item);

        return item;
    }

    public void removeItem(MyRoutineItem item) {
        items.remove(item);
    }

    public MyRoutineItem changeItem(
            Long itemId,
            ExerciseId exerciseId,
            Short targetSet,
            Short targetRep,
            BigDecimal targetWeight,
            String memo
    ) {
        MyRoutineItem item = items
                .stream()
                .filter(i -> Objects.equals(i.getId(), itemId))
                .findFirst()
                .orElse(null);

        if (item == null) {
            // TODO: item 없네
        }

        if (exerciseId != null && !exerciseId.equals(item.getExerciseId())) {
            boolean duplicated = items.stream().anyMatch(i -> i.getId().equals(itemId));

            if (duplicated) {
                // TODO: 중복이네
            }
        }

        item.internalChange(exerciseId, targetSet, targetRep, targetWeight, memo);

        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)  return false;
        if (!(o instanceof MyRoutine that)) return false;

        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : System.identityHashCode(this);
    }
}
