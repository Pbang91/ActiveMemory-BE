package com.example.activememory.record.domain.record.entity;

import com.example.activememory.record.domain.record.vo.ImageLocation;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class RecordImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false, name = "record_id")
    private Record record;

    @Column(nullable = false)
    private Short displayOrder;

    @Embedded
    private ImageLocation location;

    @CreatedDate
    private LocalDateTime createdAt;
}
