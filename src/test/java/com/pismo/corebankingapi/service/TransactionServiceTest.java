package com.pismo.corebankingapi.service;

import com.pismo.corebankingapi.entity.Account;
import com.pismo.corebankingapi.entity.Transaction;
import com.pismo.corebankingapi.exception.CreditLimitInsufficient;
import com.pismo.corebankingapi.repository.TransactionPageableRepository;
import com.pismo.corebankingapi.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TransactionServiceTest {
    private final AccountService accountService = Mockito.mock(AccountService.class);
    private final OperationTypeService tService = Mockito.mock(OperationTypeService.class);
    private final TransactionRepository repository = Mockito.mock(TransactionRepository.class);
    private final TransactionPageableRepository pageableRepository = Mockito.mock(TransactionPageableRepository.class);
    private final TransactionService service = new TransactionService(repository, pageableRepository, accountService, tService);

    @Test
    void itShouldCreateTransactionWithSuccess() {
        //TODO: Mock account dependency
        Transaction acc = Mockito.mock(Transaction.class);
        service.createTransaction(acc);

        //Add argument captor
        verify(repository).save(Mockito.any());
    }

    @Test
    void itShouldGetTransactionByIdWithSuccess() {
        Transaction acc = Mockito.mock(Transaction.class);
        when(repository.findById(1L)).thenReturn(Optional.of(acc));

        Transaction response = service.getTransaction(1L);

        assertThat(response).isNotNull();
        assertThat(response.getTransactionId()).isEqualTo(acc.getTransactionId());
        assertThat(response.getAccountId()).isEqualTo(acc.getAccountId());
        assertThat(response.getOperationTypeId()).isEqualTo(acc.getOperationTypeId());
        assertThat(response.getAmount()).isEqualTo(acc.getAmount());
        assertThat(response.getEventDate()).isEqualTo(acc.getEventDate());

    }

    @Test
    void itShouldThrowRuntimeExceptionIfTransactionNotExists() {
        assertThrows(RuntimeException.class, () -> service.getTransaction(1L));
    }

    @Test
    void itShouldThrowCreditInsufficientExceptionWhenAmountIsLessThanCredit() {
        Account.AccountBuilder accountMock = Account.builder().creditLimit(BigDecimal.valueOf(5000L));

        when(accountService.getAccount(Mockito.any())).thenReturn(accountMock.build());

        Transaction.TransactionBuilder t = Transaction.builder();
        Transaction tt = t.amount(BigDecimal.valueOf(6000L)).build();

        assertThrows(CreditLimitInsufficient.class, () -> service.createTransaction(tt));
    }
}