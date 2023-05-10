package com.pismo.corebankingapi.service;

import com.pismo.corebankingapi.entity.Account;
import com.pismo.corebankingapi.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pismo.corebankingapi.exception.ResourceNotFoundException;



@Service
@Slf4j
public class AccountService {

    private AccountRepository repository;

    @Autowired
    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account createAccount(Account account) {
        return repository.save(account);
    }

    public Account getAccount(Long accountId) {
        return repository.findById(accountId).orElseThrow(ResourceNotFoundException::new);
    }
}
