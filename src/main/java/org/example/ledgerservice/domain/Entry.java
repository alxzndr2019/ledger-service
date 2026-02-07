package org.example.ledgerservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name="entries")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID transactionId;

    @Column(nullable = false)
    private long amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    public Entry(Object o, UUID txId, UUID from, long l, String currency, Instant now) {
    }
}
