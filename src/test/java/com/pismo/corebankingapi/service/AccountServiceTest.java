package com.pismo.corebankingapi.service;

import com.pismo.corebankingapi.entity.Account;
import com.pismo.corebankingapi.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class AccountServiceTest {
    private final AccountRepository repository = Mockito.mock(AccountRepository.class);
    private final AccountService service = new AccountService(repository);

    @Test
    void itShouldCreateAccountWithSuccess() {
        Account acc = Mockito.mock(Account.class);
        service.createAccount(acc);

        verify(repository).save(Mockito.any());
    }

    @Test
    void itShouldGetAccountByIdWithSuccess() {
        Account acc = Mockito.mock(Account.class);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(acc));

        Account response = service.getAccount(1L);

        assertThat(response).isNotNull();
        assertThat(response.getAccountId()).isEqualTo(acc.getAccountId());
        assertThat(response.getDocumentNumber()).isEqualTo(acc.getDocumentNumber());

    }

    @Test
    void itShouldThrowRuntimeExceptionIfAccountNotExists() {
        assertThrows(RuntimeException.class, () -> {
            service.getAccount(1L);
        });

    }
}