package com.example.activememory.account.user.domain.repository;

import com.example.activememory.account.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public Boolean existsByEmail(String email);
}
