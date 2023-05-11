package com.pismo.corebankingapi.service;

import com.pismo.corebankingapi.entity.Account;
import com.pismo.corebankingapi.entity.OperationType;
import com.pismo.corebankingapi.entity.Transaction;
import com.pismo.corebankingapi.exception.CreditLimitInsufficient;
import com.pismo.corebankingapi.repository.TransactionPageableRepository;
import com.pismo.corebankingapi.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.pismo.corebankingapi.exception.ResourceNotFoundException;

import java.math.BigDecimal;


@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionPageableRepository pageableRepository;
    private final AccountService accountService;

    private final OperationTypeService operationTypeService;

    @Autowired
    public TransactionService(TransactionRepository repository, TransactionPageableRepository pageableRepository, AccountService accountService, OperationTypeService operationTypeService) {
        this.repository = repository;
        this.pageableRepository = pageableRepository;
        this.accountService = accountService;
        this.operationTypeService = operationTypeService;
    }

    //TODO: handle others exceptions like a could not execute statement; SQL [n/a]; constraint [fk_accounts_id]
    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        Account acc = accountService.getAccount(transaction.getAccountId());
        transactionValidator(transaction.getAmount(), acc.getCreditLimit());

        Transaction response = repository.save(transaction);

        OperationType operationType = operationTypeService.getOperationType(transaction.getOperationTypeId());

        BigDecimal newCreditLimitValue = creditLimitResolver(transaction.getAmount(), acc.getCreditLimit(), operationType);

        Account newAccountState = acc.withCreditLimit(newCreditLimitValue);
        accountService.save(newAccountState);

        return response;
    }

    public Transaction getTransaction(Long transactionId) {
        return repository.findById(transactionId).orElseThrow(ResourceNotFoundException::new);
    }

    public Page<Transaction> getPageOfTransactions(Pageable p) {
        return pageableRepository.findAll(p);
    }

    private void transactionValidator(BigDecimal transactionAmount, BigDecimal accountCreditLimit) {
        if (accountCreditLimit.compareTo(transactionAmount) == -1) {
            throw new CreditLimitInsufficient();
        }
    }

    private BigDecimal creditLimitResolver(BigDecimal transactionAmount, BigDecimal accountCreditLimit, OperationType opt) {
        BigDecimal newCreditValue = accountCreditLimit.subtract(transactionAmount);

        if (opt.getDescription().equals("PAGAMENTO")) {
            newCreditValue = accountCreditLimit.add(transactionAmount);
        }

        return newCreditValue;
    }
}
