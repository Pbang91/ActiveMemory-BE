package com.example.activememory.inventory.domain.repository;

import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.inventory.domain.entity.MyGym;
import com.example.activememory.reference.domain.gym.vo.GymId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyGymRepository extends JpaRepository<MyGym, Long> {
    boolean existsByUserIdAndGymId(UserId userId, GymId gymId);

    Optional<MyGym> findByIdAndUserId(Long value, UserId userId);
}
