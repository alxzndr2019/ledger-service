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
@Table(name = "accounts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private UUID id;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

}
