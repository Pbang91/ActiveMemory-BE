package com.example.activememory.user.domain.myroutine.entity;

import com.example.activememory.global.share.converter.ExerciseIdConverter;
import com.example.activememory.global.share.id.ExerciseId;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "my_routine_item",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_my_routine_item_my_routine_exercise",
                        columnNames = {"my_routine_id", "exercise_id"}
                )
        }
)
public class MyRoutineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    @Getter
    private Long id;

    @Column(length = 100)
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "my_routine_id", nullable = false)
    private MyRoutine myRoutine;

    @Convert(converter = ExerciseIdConverter.class)
    @Column(nullable = false, columnDefinition = "uuid")
    @Getter
    private ExerciseId exerciseId;

    @Getter
    @Embedded
    private RoutineTarget target;

    protected MyRoutineItem() {}

    MyRoutineItem(
            ExerciseId exerciseId,
            Short targetSet,
            Short targetRep,
            BigDecimal targetWeight,
            String memo
    ) {
        this.exerciseId = exerciseId;
        this.target = RoutineTarget.of(targetSet, targetRep, targetWeight);
        this.memo = memo;
    }

    void assignMyRoutine(MyRoutine myRoutine) {
        this.myRoutine = myRoutine;
    }

    void internalChange(ExerciseId exerciseId, Short targetSet, Short targetRep, BigDecimal targetWeight, String memo) {
        if (exerciseId != null) this.exerciseId = exerciseId;
        if (targetSet != null || targetRep != null || targetWeight != null) {
            this.target = this.target.update(targetSet, targetRep, targetWeight);
        }

        this.memo = memo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyRoutineItem that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : System.identityHashCode(this);
    }
}
