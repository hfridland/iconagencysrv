package com.iconagency.quotes.dto;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductForSaveDTO {
    private String product;
    private String description;
    private String htmlLink;
    private String category;
    private Long subtotal;
    private String notes;
    private Double productListPrice;
    private List<String> parts = new ArrayList<>();
}
