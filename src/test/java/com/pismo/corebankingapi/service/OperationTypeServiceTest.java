package com.pismo.corebankingapi.service;

import com.pismo.corebankingapi.entity.OperationType;
import com.pismo.corebankingapi.repository.OperationTypeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

class OperationTypeServiceTest {
    private final OperationTypeRepository repository = Mockito.mock(OperationTypeRepository.class);
    private final OperationTypeService service = new OperationTypeService(repository);

    @Test
    void itShouldCreateOperationTypeWithSuccess() {
        OperationType acc = Mockito.mock(OperationType.class);
        service.createOperationType(acc);

        verify(repository).save(Mockito.any());
    }

    @Test
    void itShouldGetOperationTypeByIdWithSuccess() {
        OperationType acc = Mockito.mock(OperationType.class);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(acc));

        OperationType response = service.getOperationType(1L);

        assertThat(response).isNotNull();
        assertThat(response.getOperationTypeId()).isEqualTo(acc.getOperationTypeId());
        assertThat(response.getDescription()).isEqualTo(acc.getDescription());

    }

    @Test
    void itShouldThrowRuntimeExceptionIfOperationTypeNotExists() {
        assertThrows(RuntimeException.class, () -> service.getOperationType(1L));

    }
}