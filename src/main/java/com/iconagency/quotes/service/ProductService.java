package com.iconagency.quotes.service;

import com.iconagency.quotes.dto.ProductDTO;
import com.iconagency.quotes.dto.ProductForSaveDTO;
import com.iconagency.quotes.entity.BOM;
import com.iconagency.quotes.entity.BomProducts;
import com.iconagency.quotes.entity.Product;
import com.iconagency.quotes.exceptions.ProductNotFound;
import com.iconagency.quotes.repository.BomRepository;
import com.iconagency.quotes.repository.ProductRepository;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    public static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BomService bomService;

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

    public boolean isProductNoInUse(String productId) {
        return productRepository.findProductByProduct(productId).isPresent();
    }

    public Product updateProduct(ProductForSaveDTO productForSaveDTO) {
        Product product = new Product();
        product.setProduct(productForSaveDTO.getProduct());
        product.setProductDescription(productForSaveDTO.getDescription());
        product.setHtmlLink(productForSaveDTO.getHtmlLink());
        product.setCategory(productForSaveDTO.getCategory());
        product.setSubtotal(productForSaveDTO.getSubtotal());
        product.setNotes(productForSaveDTO.getNotes());
        product.setProductListPrice(productForSaveDTO.getProductListPrice());

        productForSaveDTO.getParts()
                .stream()
                .forEach(part -> {
                    BomProducts bomProducts = new BomProducts();
                    bomProducts.setProduct(product);
                    BOM bom = bomService.findBomByPart(part);
                    bomProducts.setPart(bom);
                    product.getBomProducts().add(bomProducts);
                });

        return productRepository.save(product);
    }
}
