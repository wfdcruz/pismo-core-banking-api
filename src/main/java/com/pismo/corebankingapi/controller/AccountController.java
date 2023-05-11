package com.pismo.corebankingapi.controller;

import com.pismo.corebankingapi.dto.request.AccountRequest;
import com.pismo.corebankingapi.dto.response.AccountResponse;
import com.pismo.corebankingapi.entity.Account;
import com.pismo.corebankingapi.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("core-banking-api/v1/accounts")
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    AccountResponse getAccountById(@PathVariable Long accountId) {
        return service.getAccount(accountId).toResponse();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AccountResponse createAccount(@Valid @RequestBody AccountRequest dto) {
        Account.AccountBuilder builder = Account.builder();

        Account acc = builder
                .documentNumber(dto.getDocumentNumber())
                .creditLimit(dto.getCreditLimit())
                .build();

        return service.save(acc).toResponse();
    }

}
