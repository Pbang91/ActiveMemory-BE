package com.example.activememory.record.domain.record.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Getter
@Embeddable
public class ImageLocation {
    @Column(name = "object_key", nullable = false, length = 100)
    private String objectKey;

    protected ImageLocation() {}

    public ImageLocation(String objectKey) {
        this.objectKey = Objects.requireNonNull(objectKey);
    }
}
