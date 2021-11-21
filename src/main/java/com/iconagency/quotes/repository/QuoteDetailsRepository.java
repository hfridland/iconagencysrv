package com.iconagency.quotes.repository;

import com.iconagency.quotes.entity.Product;
import com.iconagency.quotes.entity.QuoteDetails;
import com.iconagency.quotes.entity.Quotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface QuoteDetailsRepository extends JpaRepository<QuoteDetails, String> {
    List<QuoteDetails> findByQuoteOrderByDetail(Long quote);
}
