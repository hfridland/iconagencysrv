package com.iconagency.quotes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
//@NoArgsConstructor
public class BomDTO {
    private String part;
    private String sku;
    private String description;
    private String category;
    private Double nonCalculatedPrice;
    private Double listPrice;
    private Double discountPercentage;

    public BomDTO() {}
}
