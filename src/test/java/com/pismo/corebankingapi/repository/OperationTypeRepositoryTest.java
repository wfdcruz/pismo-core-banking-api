package com.pismo.corebankingapi.repository;

import com.pismo.corebankingapi.entity.OperationType;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
class OperationTypeRepositoryTest {

    @Autowired
    private OperationTypeRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void itShouldCreateOperationTypeWithSuccess() {
        String description = "COMPRA_A_VISTA";
        OperationType.OperationTypeBuilder builder = OperationType.builder();
        OperationType acc = builder.description(description).build();

        OperationType response = repository.save(acc);

        AssertionsForClassTypes.assertThat(response.getOperationTypeId()).isPositive();
        AssertionsForClassTypes.assertThat(response.getDescription()).isEqualTo(description);
    }

    @Test
    void itShouldGetOperationTypeByIdWithSuccess() {
        String description = "COMPRA_A_VISTA";
        OperationType.OperationTypeBuilder builder = OperationType.builder();
        OperationType acc = builder.description(description).build();
        OperationType persistedOperationType = repository.save(acc);

        Optional<OperationType> response = repository
                .findById(persistedOperationType.getOperationTypeId());

        AssertionsForClassTypes.assertThat(response.isPresent()).isTrue();
        AssertionsForClassTypes.assertThat(response.get().getDescription())
                .isEqualTo(description);
    }


}