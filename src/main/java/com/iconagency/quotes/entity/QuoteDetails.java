package com.iconagency.quotes.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "quote_details")
//@IdClass(QuoteDetailsId.class)
//@NoArgsConstructor
public class QuoteDetails {
    @Id
    @Column(name = "`detail#`")
    private Long detail;

    //@Id
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "`quote#`", nullable = false)
    private Quotes quotes;*/

    @Column(name = "`quote#`")
    private Long quote;

    @Column(name = "`tag#`")
    private String tag;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "`product#`", referencedColumnName = "`product#`")
    private Product product;*/

    @Column(name = "`product#`")
    private String product;
}
