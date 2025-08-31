package com.example.activememory.record.domain.record.vo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class RecordMetricItem {
    @Column(name = "set_no", nullable = false)
    private Short set;

    @Column(nullable = false)
    private Short rep;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal weight;

    @Column
    private String memo;

    protected RecordMetricItem() {}

    private RecordMetricItem(Short set, Short rep, BigDecimal weight, String memo) {
        this.set = set;
        this.rep = rep;
        this.weight = weight;
        this.memo = memo;
    }

    public static RecordMetricItem of(Short set, Short rep, BigDecimal weight, String memo) {
        return new RecordMetricItem(set, rep, weight, memo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecordMetricItem that)) return false;

        return Objects.equals(set, that.set)
                && Objects.equals(rep, that.rep)
                && Objects.equals(weight, that.weight)
                && Objects.equals(memo, that.memo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set, rep, weight, memo);
    }
}
