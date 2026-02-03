package com.example.activememory.reference.domain.exercise.repository;

import com.example.activememory.reference.domain.exercise.entity.BodyPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyPartRepository extends JpaRepository<BodyPart, Long> {
}
