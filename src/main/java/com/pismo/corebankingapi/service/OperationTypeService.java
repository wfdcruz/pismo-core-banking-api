package com.pismo.corebankingapi.service;

import com.pismo.corebankingapi.entity.OperationType;
import com.pismo.corebankingapi.exception.ResourceNotFoundException;
import com.pismo.corebankingapi.repository.OperationTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationTypeService {

    private final OperationTypeRepository repository;

    public OperationTypeService(OperationTypeRepository repository) {
        this.repository = repository;
    }

    public OperationType getOperationType(Long operationTypeId) {
        return repository.findById(operationTypeId).orElseThrow(ResourceNotFoundException::new);
    }

    public OperationType createOperationType(OperationType operationType) {
        return repository.save(operationType);
    }
}
