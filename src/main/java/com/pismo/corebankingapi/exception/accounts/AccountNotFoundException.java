package com.pismo.corebankingapi.exception.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Account not exists")
public class AccountNotFoundException extends Exception {
}
