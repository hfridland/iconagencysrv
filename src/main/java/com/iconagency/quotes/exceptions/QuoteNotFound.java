package com.iconagency.quotes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuoteNotFound extends RuntimeException {
    public QuoteNotFound(String message) {
        super(message);
    }
}
