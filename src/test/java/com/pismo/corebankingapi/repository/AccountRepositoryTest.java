package com.pismo.corebankingapi.repository;

import com.pismo.corebankingapi.entity.Account;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void itShouldCreateAccountWithSuccess() {
        String documentNumber = "123123";
        Account.AccountBuilder builder = Account.builder();
        Account acc = builder.documentNumber(documentNumber).build();

        Account response = repository.save(acc);

        AssertionsForClassTypes.assertThat(response.getAccountId()).isPositive();
        AssertionsForClassTypes.assertThat(response.getDocumentNumber()).isEqualTo(documentNumber);
    }

    @Test
    void itShouldGetAccountByIdWithSuccess() {
        String documentNumber = "123123";
        Account.AccountBuilder builder = Account.builder();
        Account acc = builder.documentNumber(documentNumber).build();
        Account persistedAccount = repository.save(acc);

        Optional<Account> response = repository
                .findById(persistedAccount.getAccountId());

        AssertionsForClassTypes.assertThat(response.isPresent()).isTrue();
        AssertionsForClassTypes.assertThat(response.get().getDocumentNumber())
                .isEqualTo(documentNumber);
    }


}