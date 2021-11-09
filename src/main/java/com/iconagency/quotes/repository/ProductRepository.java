package com.iconagency.quotes.repository;

import com.iconagency.quotes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query(value = "SELECT DISTINCT category FROM product ORDER BY category", nativeQuery = true)
    Collection<String> findAllCategories();

    List<Product> findByCategoryOrderByProduct(String category);

    Optional<Product> findProductByProduct(String product);

}
