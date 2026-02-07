package org.example.ledgerservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.ledgerservice.domain.Entry;
import org.example.ledgerservice.domain.Transaction;
import org.example.ledgerservice.exception.DuplicateTransactionException;
import org.example.ledgerservice.exception.InsufficentFundsException;
import org.example.ledgerservice.repository.AccountRepository;
import org.example.ledgerservice.repository.EntryRepository;
import org.example.ledgerservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LedgerService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final EntryRepository entryRepository;

    @Transactional()
    public UUID transfer(
            UUID from,
            UUID to,
            long amount,
            String currency,
            String idempotencyKey
    ){
        //1. Idempotency Check
        transactionRepository.findByIdempotencyKey(idempotencyKey).ifPresent(tx->{
                throw new DuplicateTransactionException(tx.getId());
        });

        //2. Create transaction
        UUID txId = UUID.randomUUID();
        transactionRepository.save(new Transaction(txId, idempotencyKey, "PENDING", Instant.now()));
        //3. Lock accounts
        accountRepository.lockById(from);
        accountRepository.lockById(to);

        //4. Check balance
        long balance = entryRepository.getBalance(from);
        if(balance < amount){
            throw new InsufficentFundsException("Insufficent amount");
        }
        //5. Double Entry
        entryRepository.save(new Entry(null, txId, from, -amount, currency, Instant.now()));
        entryRepository.save(new Entry(null, txId, to, amount, currency, Instant.now()));

        //6. Commit Transaction
        transactionRepository.save(new Transaction(txId, idempotencyKey, "COMMITTED", Instant.now()));

        return txId;
    }
}
