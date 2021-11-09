package com.iconagency.quotes.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BomProductsId implements Serializable {
    private String product;
    private String part;

    public BomProductsId(String product, String part) {
        this.product = product;
        this.part = part;
    }
}
