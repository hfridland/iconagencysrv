package com.iconagency.quotes.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "quotes")
public class Quotes {
    @Id
    @Column(name = "`Quote#`")
    private Long quote;
    @Column(name = "`File#`")
    private String file;
    @Column(name = "job_name")
    private String jobName;
    @Column(name = "Engineer")
    private String engineer;
    @Column(name = "Date")
    private Date date;

    /*@OneToMany(mappedBy = "quotes", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<QuoteDetails> quoteDetails = new ArrayList<>();*/
}
