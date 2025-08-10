package com.example.activememory.record.domain.record.entity;

import com.example.activememory.global.share.converter.ExerciseIdConverter;
import com.example.activememory.global.share.id.ExerciseId;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class RecordMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    private Short displayOrder;

    @Column(nullable = false)
    private Short rep;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal weight;

    @Column
    private String memo;

    @Convert(converter = ExerciseIdConverter.class)
    @Column(nullable = false, columnDefinition = "uuid")
    private ExerciseId exerciseId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name = "record_id")
    private Record record;

    protected RecordMetric() {}

    RecordMetric(ExerciseId exerciseId, Short displayOrder, Short rep, BigDecimal weight, String memo) {
        this.exerciseId = exerciseId;
        this.displayOrder = displayOrder;
        this.rep = rep;
        this.weight = weight;
        this.memo = memo;
    }

    void assignRecord(Record record) {
        this.record = record;
    }

    public void change(Short displayOrder, Short rep, BigDecimal weight, String memo) {
        if (displayOrder != null) this.displayOrder = displayOrder;
        if (rep != null) this.rep = rep;
        if (weight != null) this.weight = weight;

        this.memo = memo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)  return false;
        if (!(o instanceof RecordMetric that)) return false;

        return Objects.equals(id, that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
