package com.iconagency.quotes.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bom")
public class BOM {
    @Id
    @Column(name = "`part#`")
    private String part;
    private String sku;
    private String description;
    private String category;
    @Column(name = "`non-calculated-price`")
    private Double nonCalculatedPrice;
    @Column(name = "`list-price`")
    private Double listPrice;
    @Column(name = "`discount-percentage`")
    private Double discountPercentage;
}
