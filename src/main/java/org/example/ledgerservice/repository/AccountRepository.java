package org.example.ledgerservice.repository;

import org.example.ledgerservice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query(value = "SELECT 1 FROM accounts WHERE id = :id FOR UPDATE", nativeQuery = true)
    void lockById(@Param("id") UUID id);
}
