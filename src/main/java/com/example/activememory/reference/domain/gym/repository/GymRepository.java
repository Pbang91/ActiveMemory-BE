package com.example.activememory.reference.domain.gym.repository;

import com.example.activememory.reference.domain.gym.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymRepository extends JpaRepository<Gym, Long> {
    Optional<Gym> findByProviderId(String s);
}
