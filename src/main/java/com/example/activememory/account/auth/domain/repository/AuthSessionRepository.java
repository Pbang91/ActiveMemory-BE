package com.example.activememory.account.auth.domain.repository;

import com.example.activememory.account.auth.domain.entity.AuthSession;
import org.springframework.data.repository.CrudRepository;

public interface AuthSessionRepository extends CrudRepository<AuthSession, Long> {
}
