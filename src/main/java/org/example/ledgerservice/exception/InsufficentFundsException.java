package org.example.ledgerservice.exception;

public class InsufficentFundsException extends RuntimeException {
    public InsufficentFundsException(String message) {
        super(message);
    }
}
