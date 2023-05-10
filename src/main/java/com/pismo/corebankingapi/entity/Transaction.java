package com.pismo.corebankingapi.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table("transactions")
public class Transaction {
    @Id
    private Long transactionId;

    private Account accountId;

    private OperationType operationTypeId;
    private BigDecimal amount;

    private LocalDateTime eventDate;
}
