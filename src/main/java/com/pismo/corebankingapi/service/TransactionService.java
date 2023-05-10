package com.pismo.corebankingapi.service;

import com.pismo.corebankingapi.entity.Account;
import com.pismo.corebankingapi.exception.accounts.AccountNotFoundException;
import com.pismo.corebankingapi.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AccountService {

    private AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Mono<Account> createAccount(Account account) {
        return repository.save(account);
    }

    public Mono<Account> getAccount(Long accountId) {
        return repository.findById(accountId)
                .switchIfEmpty(Mono.error(new AccountNotFoundException()));
    }
}
