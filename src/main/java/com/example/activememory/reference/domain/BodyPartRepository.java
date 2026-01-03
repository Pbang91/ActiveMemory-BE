package com.example.activememory.reference.domain;

import com.example.activememory.reference.domain.entity.BodyPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyPartRepository extends JpaRepository<BodyPart, Long> {
}
