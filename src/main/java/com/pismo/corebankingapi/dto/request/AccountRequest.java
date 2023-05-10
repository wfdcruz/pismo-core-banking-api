package com.pismo.corebankingapi.dto.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private static final int DOCUMENT_NUMBER_SIZE = 11;

    @NotNull
    @NotEmpty
    @Size(min = DOCUMENT_NUMBER_SIZE, max = DOCUMENT_NUMBER_SIZE)
    private String documentNumber;
}
