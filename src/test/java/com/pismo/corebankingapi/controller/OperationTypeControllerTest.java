package com.pismo.corebankingapi.controller;

import com.pismo.corebankingapi.dto.request.OperationTypeRequest;
import com.pismo.corebankingapi.dto.response.OperationTypeResponse;
import com.pismo.corebankingapi.entity.OperationType;
import com.pismo.corebankingapi.service.OperationTypeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

class OperationTypeControllerTest {
    private final OperationTypeService service = Mockito.mock(OperationTypeService.class);
    private final OperationTypeController controller = new OperationTypeController(service);

    @Test
    void itShouldReturnOperationTypeWithSuccess() {
        OperationType OperationTypeMock = new OperationType(1L, "COMPRA_A_VISTA");
        when(service.getOperationType(1L)).thenReturn(OperationTypeMock);

        OperationTypeResponse response = controller.getOperationTypeById(OperationTypeMock.getOperationTypeId());

        assertThat(response.getOperationTypeId()).isEqualTo(OperationTypeMock.getOperationTypeId());
        assertThat(response.getDescription()).isEqualTo(OperationTypeMock.getDescription());
    }

    @Test
    void itShouldCreateOperationTypeWithSuccess() {
        OperationType OperationTypeMock = new OperationType(1L, "COMPRA_A_VISTA");
        when(service.createOperationType(Mockito.any())).thenReturn(OperationTypeMock);

        OperationTypeRequest accRequest = new OperationTypeRequest(OperationTypeMock.getDescription());
        OperationTypeResponse response = controller.createOperationType(accRequest);

        assertThat(response.getOperationTypeId()).isEqualTo(OperationTypeMock.getOperationTypeId());
        assertThat(response.getDescription()).isEqualTo(OperationTypeMock.getDescription());
    }
}