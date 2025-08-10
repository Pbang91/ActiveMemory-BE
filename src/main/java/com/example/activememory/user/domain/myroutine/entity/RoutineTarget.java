package com.example.activememory.user.domain.myroutine.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class RoutineTarget {
    @Column(nullable = false, columnDefinition = "smallint")
    private Short targetSet;

    @Column(nullable = false, columnDefinition = "smallint")
    private Short targetRep;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal targetWeight;

    protected RoutineTarget() {}

    private RoutineTarget(Short targetSet, Short targetRep, BigDecimal targetWeight) {
        this.targetSet = Objects.requireNonNull(targetSet);
        this.targetRep = Objects.requireNonNull(targetRep);
        this.targetWeight = Objects.requireNonNull(targetWeight);
    }

    public static RoutineTarget of(Short targetSet, Short targetRep, BigDecimal targetWeight) {
        return new RoutineTarget(targetSet, targetRep, targetWeight);
    }

    public RoutineTarget update(Short targetSet, Short targetRep, BigDecimal targetWeight) {
        return new RoutineTarget(
                targetSet != null ? targetSet : this.targetSet,
                targetRep != null ? targetRep : this.targetRep,
                targetWeight != null ? targetWeight : this.targetWeight
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof RoutineTarget that)) return false;

        return Objects.equals(targetSet, that.targetSet)
                && Objects.equals(targetRep, that.targetRep)
                && Objects.equals(targetWeight, that.targetWeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetSet, targetRep, targetWeight);
    }
}
