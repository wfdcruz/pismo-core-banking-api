package com.pismo.corebankingapi.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.pismo.corebankingapi.dto.response.TransactionResponse;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@With
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(name = "transaction_seq", sequenceName = "transactions_transaction_id_seq", allocationSize = 1)
    private Long transactionId;

    private Long accountId;

    private Long operationTypeId;

    private BigDecimal amount;

    private LocalDateTime eventDate;


    public TransactionResponse toResponse() {
        TransactionResponse.TransactionResponseBuilder builder = TransactionResponse.builder();
        return builder.transactionId(this.transactionId)
                .accountId(this.accountId)
                .operationTypeId(this.operationTypeId)
                .amount(this.amount)
                .build();
    }
}
