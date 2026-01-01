package com.example.activememory.account.auth.domain;

import org.springframework.data.repository.CrudRepository;

public interface AuthSessionRepository extends CrudRepository<AuthSession, Long> {
}
