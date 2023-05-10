package com.pismo.corebankingapi.controller;

import com.pismo.corebankingapi.dto.request.TransactionRequest;
import com.pismo.corebankingapi.dto.response.TransactionResponse;
import com.pismo.corebankingapi.entity.Transaction;
import com.pismo.corebankingapi.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class TransactionControllerTest {
    private final TransactionService service = Mockito.mock(TransactionService.class);
    private final TransactionController controller = new TransactionController(service);

    @Test
    void itShouldReturnTransactionWithSuccess() {
        Transaction transactionMock = new Transaction(1L, 1L, 1L, BigDecimal.ONE, LocalDateTime.now(ZoneOffset.UTC));
        when(service.getTransaction(1L)).thenReturn(transactionMock);

        TransactionResponse response = controller.getTransactionById(transactionMock.getTransactionId());

        assertThat(response.getTransactionId()).isEqualTo(transactionMock.getTransactionId());
        assertThat(response.getAccountId()).isEqualTo(transactionMock.getAccountId());
        assertThat(response.getOperationTypeId()).isEqualTo(transactionMock.getOperationTypeId());
        assertThat(response.getAmount()).isEqualTo(transactionMock.getAmount());
    }

    @Test
    void itShouldCreateTransactionWithSuccess() {
        Transaction transactionMock = new Transaction(1L, 1L, 1L, BigDecimal.ONE, LocalDateTime.now(ZoneOffset.UTC));
        when(service.createTransaction(Mockito.any())).thenReturn(transactionMock);

        TransactionRequest accRequest = new TransactionRequest(transactionMock.getAccountId(), transactionMock.getOperationTypeId(), transactionMock.getAmount());
        TransactionResponse response = controller.createTransaction(accRequest);

        assertThat(response.getTransactionId()).isEqualTo(transactionMock.getTransactionId());
        assertThat(response.getAccountId()).isEqualTo(transactionMock.getAccountId());
        assertThat(response.getOperationTypeId()).isEqualTo(transactionMock.getOperationTypeId());
        assertThat(response.getAmount()).isEqualTo(transactionMock.getAmount());
    }
}