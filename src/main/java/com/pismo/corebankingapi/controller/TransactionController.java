package com.pismo.corebankingapi.controller;

import com.pismo.corebankingapi.entity.Account;
import com.pismo.corebankingapi.service.AccountService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("api/v1/accounts")
@Slf4j
public class AccountController {

    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    Mono<Account> getAccountById(@PathVariable Long accountId) {
        return service.getAccount(accountId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<Account> createAccount(@Valid @RequestBody Account acc) {
        return service.createAccount(acc);
    }

}
