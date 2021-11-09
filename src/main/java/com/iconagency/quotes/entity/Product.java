package com.iconagency.quotes.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
public class Product {
    //product#
    @Id
    @Column(name = "`product#`")
    private String product;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "html_link")
    private String htmlLink;
    private String category;
    private Long subtotal;
    @Type(type="text")
    private String notes;
    @Column(name = "product_list_price")
    private Double productListPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BomProducts> bomProducts = new ArrayList<>();
}
