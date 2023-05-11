package com.pismo.corebankingapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Credit Insufficient")
public class CreditLimitInsufficient extends RuntimeException {
}
