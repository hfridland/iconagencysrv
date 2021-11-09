package com.iconagency.quotes.web;

import com.iconagency.quotes.dto.ProductDTO;
import com.iconagency.quotes.dto.ProductHdrDTO;
import com.iconagency.quotes.entity.Product;
import com.iconagency.quotes.facade.ProductFacade;
import com.iconagency.quotes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductFacade productFacade;
    @Autowired
    private ProductService productService;

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> res = productService.getAllCategories();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductHdrDTO>> getProductsByCategory(@PathVariable("category") String category) {
         List<ProductHdrDTO> products = productService.getProductsByCategory(category)
                .stream()
                 .map(productFacade::productToProductHdrDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductByProductId(@PathVariable("productId") String productId) {
        Product product = productService.getProductByProductId(productId);
        ProductDTO productDTO = productFacade.productToProductDTO(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

}
