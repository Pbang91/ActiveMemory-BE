package com.example.activememory.reference.domain.entity;

import com.example.activememory.reference.domain.vo.BodyPartCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "body_parts")
public class BodyPart {
    @Id
    @Column(nullable = false, length = 10)
    private String code;

    @Column(nullable = false, length = 40)
    private String name;

    public BodyPartCode getBodyPartCode() {
        return BodyPartCode.of(code);
    }
}
