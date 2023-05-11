package com.pismo.corebankingapi.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.pismo.corebankingapi.dto.response.AccountResponse;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "accounts_account_id_seq", allocationSize = 1)
    private Long accountId;
    private String documentNumber;

    private BigDecimal creditLimit;

    public AccountResponse toResponse() {
        return new AccountResponse(this.accountId, this.documentNumber, this.creditLimit);
    }

}
