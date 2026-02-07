package org.example.ledgerservice.exception;

import java.util.UUID;

public class DuplicateTransactionException extends RuntimeException {
    public DuplicateTransactionException(UUID message) {
        super(String.valueOf(message));
    }
}
