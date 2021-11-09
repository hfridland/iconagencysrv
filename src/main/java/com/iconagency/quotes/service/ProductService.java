package com.iconagency.quotes.service;

import com.iconagency.quotes.entity.Product;
import com.iconagency.quotes.exceptions.ProductNotFound;
import com.iconagency.quotes.repository.BomRepository;
import com.iconagency.quotes.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    public static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public List<String> getAllCategories() {
        return productRepository.findAllCategories()
                .stream()
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryOrderByProduct(category)
                .stream()
                .collect(Collectors.toList());
    }

    public Product getProductByProductId(String productId) {
        return productRepository.findProductByProduct(productId)
                .orElseThrow(() -> new ProductNotFound("Product not found"));
    }


}
