package com.example.activememory.reference.domain.exercise.entity;

import com.example.activememory.reference.domain.exercise.vo.BodyPartCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "body_parts")
public class BodyPart {
    @Id
    @Column(nullable = false, length = 10)
    private String code;

    @Getter
    @Column(nullable = false, length = 40)
    private String name;

    public BodyPartCode getBodyPartCode() {
        return BodyPartCode.of(code);
    }
}
