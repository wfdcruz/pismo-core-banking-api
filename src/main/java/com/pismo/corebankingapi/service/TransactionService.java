package com.pismo.corebankingapi.service;

import com.pismo.corebankingapi.entity.Transaction;
import com.pismo.corebankingapi.repository.TransactionPageableRepository;
import com.pismo.corebankingapi.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.pismo.corebankingapi.exception.ResourceNotFoundException;


@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionPageableRepository pageableRepository;

    @Autowired
    public TransactionService(TransactionRepository repository, TransactionPageableRepository pageableRepository) {
        this.repository = repository;
        this.pageableRepository = pageableRepository;
    }

    //TODO: handle others exceptions like a could not execute statement; SQL [n/a]; constraint [fk_accounts_id]
    public Transaction createTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    public Transaction getTransaction(Long transactionId) {
        return repository.findById(transactionId).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Transaction> getPageOfTransactions(Pageable p) {
        return pageableRepository.findAll(p);
    }
}
