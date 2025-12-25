package com.example.activememory.record.domain.entity;

import com.example.activememory.global.enums.PerformableType;
import jakarta.persistence.*;

@Entity
@Table(name = "workout_logs")
public class WorkoutLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "snapshot_name")
    private String snapshotName;

    @Column(name = "snapshot_body_part_code", length = 10)
    private String snapshotBodyPartCode;

    @Column(name = "memo")
    private String memo;

    @Enumerated(EnumType.STRING)
    @Column(name = "performable_type", nullable = false)
    private PerformableType performableType;

    @Column(name = "performable_id", nullable = false)
    private Long performableId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;
}
