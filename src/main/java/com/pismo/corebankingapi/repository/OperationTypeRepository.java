package com.pismo.corebankingapi.repository;

import com.pismo.corebankingapi.entity.OperationType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeRepository extends CrudRepository<OperationType, Long> {
}
