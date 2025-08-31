package com.example.activememory.record.domain.record.entity;

import com.example.activememory.global.share.converter.ExerciseIdConverter;
import com.example.activememory.global.share.id.ExerciseId;
import com.example.activememory.record.domain.record.vo.RecordMetricItem;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class RecordMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Convert(converter = ExerciseIdConverter.class)
    @Column(nullable = false, columnDefinition = "uuid")
    private ExerciseId exerciseId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name = "record_id")
    private Record record;

    @Column(nullable = false)
    private Short displayOrder;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "record_metric_item",
            joinColumns = @JoinColumn(name = "record_metric_id"),
            uniqueConstraints = {
                    @UniqueConstraint(
                            name = "uk_record_metric_item_record_metric_set_no",
                            columnNames = {"record_metric_id", "set_no"}
                    )
            },
            indexes = {
                    @Index(name = "idx_record_metric_item_record_metric_id", columnList = "record_metric_id")
            }
    )
    @OrderBy("set ASC")
    private List<RecordMetricItem> items = new ArrayList<>();

    protected RecordMetric() {}

    private RecordMetric(
            Record record,
            ExerciseId exerciseId,
            Short displayOrder,
            List<RecordMetricItem> items
    ) {
        this.record = record;
        this.exerciseId = Objects.requireNonNull(exerciseId);
        this.displayOrder = Objects.requireNonNull(displayOrder);
        this.items = new ArrayList<>(items);
    }

    static RecordMetric of(
            Record record,
            ExerciseId exerciseId,
            Short displayOrder,
            List<RecordMetricItem> items
    ) {
        // TODO: 검증 식 진행
        return new RecordMetric(record, exerciseId, displayOrder, items);
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
