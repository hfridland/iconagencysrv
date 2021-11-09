package com.iconagency.quotes.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "non_calculated_price")
    private Double nonCalculatedPrice;
    @Column(name = "list_price")
    private Double listPrice;
    @Column(name = "discount_percentage")
    private Double discountPercentage;
}
