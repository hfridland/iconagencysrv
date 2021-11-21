package com.iconagency.quotes.repository;

import com.iconagency.quotes.entity.Quotes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuotesRepository extends JpaRepository<Quotes, Long> {
    Optional<Quotes> findQuotesByQuote(Long quote);
}
