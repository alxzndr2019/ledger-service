package org.example.ledgerservice.repository;

import org.example.ledgerservice.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Query("""
        SELECT COALESCE(SUM(e.amount), 0)
        FROM Entry e
        WHERE e.accountId = :accountId
    """)
    long getBalance(@Param("accountId") UUID accountId);
}
