package com.pismo.corebankingapi.controller;

import com.pismo.corebankingapi.dto.request.TransactionRequest;
import com.pismo.corebankingapi.dto.response.TransactionResponse;
import com.pismo.corebankingapi.entity.Transaction;
import com.pismo.corebankingapi.service.TransactionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("core-banking-api/v1/transactions")
@Slf4j
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    Page<TransactionResponse> getTransactions(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int size
                                              ) {
        PageRequest pr = PageRequest.of(page, size, Sort.by("transactionId").descending());

        return service.getPageOfTransactions(pr).map(Transaction::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest dto) {
        Transaction.TransactionBuilder builder = Transaction.builder();

        Transaction transaction = builder.accountId(dto.getAccountId()).operationTypeId(dto.getOperationTypeId()).amount(dto.getAmount()).eventDate(LocalDateTime.now(ZoneOffset.UTC)).build();

        return service.createTransaction(transaction).toResponse();
    }

    @GetMapping("/{transactionId}")
    TransactionResponse getTransactionById(@PathVariable Long transactionId) {
        return service.getTransaction(transactionId).toResponse();
    }

}
