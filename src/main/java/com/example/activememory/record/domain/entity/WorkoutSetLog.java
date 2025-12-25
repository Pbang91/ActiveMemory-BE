package com.example.activememory.record.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Entity
@Table(name = "workout_set_logs")
public class WorkoutSetLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "set_num", nullable = false)
    private Integer setNum;

    @Column(name = "reps", nullable = false)
    private Integer reps;

    @Column(name = "weight", precision = 5, scale = 2)
    private BigDecimal weight;

    @ColumnDefault("false")
    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_log_id", nullable = false)
    private WorkoutLog workoutLog;
}
