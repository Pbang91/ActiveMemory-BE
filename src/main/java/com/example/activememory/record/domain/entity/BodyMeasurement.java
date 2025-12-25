package com.example.activememory.record.domain.entity;

import com.example.activememory.account.domain.vo.UserId;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "body_measurements")
public class BodyMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "weight", precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(name = "skeletal_muscle", precision = 5, scale = 2)
    private BigDecimal skeletalMuscle;

    @Column(name = "body_fat", precision = 5, scale = 2)
    private BigDecimal bodyFat;

    @Column(name = "measured_date", nullable = false)
    private LocalDate measuredDate;

    @Column(name = "user_id", nullable = false)
    private UserId userId;
}
