package com.iconagency.quotes.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;

@Data
@Entity
@Table(name = "`bom_products`")
@IdClass(BomProductsId.class)
@NoArgsConstructor
public class BomProducts {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`product#`", nullable = false)
    private Product product;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "`part#`", referencedColumnName = "`part#`")
    private BOM part;
}
