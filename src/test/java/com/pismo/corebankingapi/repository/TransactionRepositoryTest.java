package com.pismo.corebankingapi.repository;

import com.pismo.corebankingapi.entity.Transaction;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationTypeRepository operationTypeRepository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void itShouldCreateTransactionWithSuccess() {
        LocalDateTime dateTime = LocalDateTime.now(ZoneOffset.UTC);
        Transaction.TransactionBuilder builder = Transaction.builder();
        Transaction acc = builder.accountId(1L)
                .operationTypeId(1L)
                .amount(BigDecimal.ONE)
                .eventDate(dateTime)
                .build();

        Transaction response = repository.save(acc);

        AssertionsForClassTypes.assertThat(response.getTransactionId()).isPositive();
        AssertionsForClassTypes.assertThat(response.getAccountId()).isEqualTo(1L);
        AssertionsForClassTypes.assertThat(response.getOperationTypeId()).isEqualTo(1L);
        AssertionsForClassTypes.assertThat(response.getAmount()).isEqualTo(BigDecimal.ONE);
        AssertionsForClassTypes.assertThat(response.getEventDate()).isEqualTo(dateTime);
    }


}