package org.example.ledgerservice.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ledgerservice.repository.TransferRequest;
import org.example.ledgerservice.service.LedgerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/ledger")
@RequiredArgsConstructor
public class LedgerController {
    private final LedgerService ledgerService;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody @Valid TransferRequest req){
        UUID txid = ledgerService.transfer(
                req.fromAccount(),
                req.toAccount(),
                req.amount(),
                req.currency(),
                req.idempotencyKey()
        );
        return ResponseEntity.ok(Map.of("transactionId",txid));
    }
}
