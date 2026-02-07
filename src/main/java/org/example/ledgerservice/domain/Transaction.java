package org.example.ledgerservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String idempotencyKey;

    @Column
    private String status;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();
}
