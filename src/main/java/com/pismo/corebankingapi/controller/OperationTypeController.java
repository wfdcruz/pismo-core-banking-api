package com.pismo.corebankingapi.controller;

import com.pismo.corebankingapi.dto.request.OperationTypeRequest;
import com.pismo.corebankingapi.dto.response.OperationTypeResponse;
import com.pismo.corebankingapi.entity.OperationType;
import com.pismo.corebankingapi.service.OperationTypeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("core-banking-api/v1/operationsType")
@Slf4j
public class OperationTypeController {

    private final OperationTypeService service;

    @Autowired
    public OperationTypeController(OperationTypeService service) {
        this.service = service;
    }


    @GetMapping("{operationTypeId}")
    OperationTypeResponse getOperationTypeById(@PathVariable Long operationTypeId) {
        return service.getOperationType(operationTypeId).toResponse();
    }

    @PostMapping
    OperationTypeResponse createOperationType(@Valid @RequestBody OperationTypeRequest dto) {
        OperationType.OperationTypeBuilder builder = OperationType.builder();
        builder.description(dto.getDescription());

        return service.createOperationType(builder.build()).toResponse();
    }
}
