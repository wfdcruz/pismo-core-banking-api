package com.pismo.corebankingapi.controller;

import com.pismo.corebankingapi.dto.request.AccountRequest;
import com.pismo.corebankingapi.dto.response.AccountResponse;
import com.pismo.corebankingapi.entity.Account;
import com.pismo.corebankingapi.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class AccountControllerTest {
    private final AccountService service = Mockito.mock(AccountService.class);
    private final AccountController controller = new AccountController(service);

    @Test
    void itShouldReturnAccountWithSuccess() {
        Account accountMock = new Account(1L, "123", BigDecimal.ONE);
        when(service.getAccount(1L)).thenReturn(accountMock);

        AccountResponse response = controller.getAccountById(accountMock.getAccountId());

        assertThat(response.getAccountId()).isEqualTo(accountMock.getAccountId());
        assertThat(response.getDocumentNumber()).isEqualTo(accountMock.getDocumentNumber());
        assertThat(response.getCreditLimit()).isEqualTo(accountMock.getCreditLimit());
    }

    @Test
    void itShouldCreateAccountWithSuccess() {
        Account accountMock = new Account(1L, "123",  BigDecimal.ONE);
        when(service.save(Mockito.any())).thenReturn(accountMock);

        AccountRequest accRequest = new AccountRequest(accountMock.getDocumentNumber(), accountMock.getCreditLimit());
        AccountResponse response = controller.createAccount(accRequest);

        assertThat(response.getAccountId()).isEqualTo(accountMock.getAccountId());
        assertThat(response.getDocumentNumber()).isEqualTo(accountMock.getDocumentNumber());
        assertThat(response.getCreditLimit()).isEqualTo(accountMock.getCreditLimit());
    }

}