package org.example.ledgerservice.repository;

import java.util.UUID;

public record TransferRequest(
        UUID fromAccount,
        UUID toAccount,
        long amount,
        String currency,
        String idempotencyKey
) {
}
