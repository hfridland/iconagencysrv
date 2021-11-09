package com.iconagency.quotes.dto;

import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {
    private String product;
    private String productDescription;
    private String htmlLink;
    private String category;
    private Long subtotal;
    private String notes;
    private Double productListPrice;
    private List<BomDTO> parts = new ArrayList<>();
}
