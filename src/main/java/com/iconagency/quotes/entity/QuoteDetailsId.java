package com.iconagency.quotes.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class QuoteDetailsId implements Serializable {
    private Long detail;
    private Long quotes;

    public QuoteDetailsId(Long detail, Long quotes) {
        this.detail = detail;
        this.quotes = quotes;
    }
}
