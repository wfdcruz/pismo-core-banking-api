package com.pismo.corebankingapi.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.pismo.corebankingapi.dto.response.OperationTypeResponse;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity
@Table(name="operation_types")
public class OperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_type_seq")
    @SequenceGenerator(name = "operation_type_seq", sequenceName = "operations_type_operation_type_id_seq", allocationSize = 1)
    private Long operationTypeId;

    private String description;

    public OperationTypeResponse toResponse() {
        return new OperationTypeResponse(this.operationTypeId, this.description);
    }
}
